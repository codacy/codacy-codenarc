package codacy.codenarc.docs

import java.lang.reflect.Field

import better.files._
import com.codacy.plugins.api.results.{Parameter, Pattern, Result, Tool}
import play.api.libs.json.Json

import scala.jdk.CollectionConverters._
import org.reflections.Reflections

import scala.annotation.tailrec
import scala.util.Try

object DocGenerator {
  private val toolName = "codenarc"

  private val codeNarcVersionFile = ".codenarc-version"
  private val codeNarcGitRepository = "github.com/CodeNarc/CodeNarc"
  private val patternsJsonFile = "patterns.json"
  private val descriptionJsonFile = "description.json"
  private val docsFolder = "docs"
  private val descriptionFolder = "description"

  private val codeNarcDocumentationStartingPoint = "codenarc-rule-index.md"

  private val reflections = new Reflections("org.codenarc.rule")
  private val subTypes = reflections.getSubTypesOf(classOf[org.codenarc.rule.AbstractAstVisitorRule]).asScala

  case class RuleInformation(
      patternId: Pattern.Id,
      description: String,
      descriptionExtended: String = "",
      priority: Option[RulePriority],
      parameters: Option[Set[String]]
  )

  case class RulePriority(priority: Option[String], priorityType: Option[String])

  private val defaultPriority = "3"
  private val defaultRuleType = "CodeStyle"
  private val defaultRulePriority = RulePriority(Some(defaultPriority), Some(defaultRuleType))

  /**
    * Converts from CodeNarc's complexity to Codacy level
    */
  val levelFromPriority: PartialFunction[Option[String], Result.Level.Value] = {
    case Some("1") => Result.Level.Err
    case Some("2") => Result.Level.Warn
    case Some("3") => Result.Level.Info
    case _ => Result.Level.Info
  }

  val categoryTypeFromCategory: PartialFunction[Option[String], Pattern.Category.Value] = {
    case Some("security") => Pattern.Category.Security
    case Some("unused") => Pattern.Category.UnusedCode
    case _ => Pattern.Category.CodeStyle
  }

  /**
    * Convert from list of parameters to Parameter.Specification list
    */
  def parameterSpecificationFromParametersStringList(
      parameters: Option[Set[String]]
  ): Option[Set[Parameter.Specification]] =
    parameters.map(
      paramList => paramList.map(param => Parameter.Specification(Parameter.Name(param), Parameter.Value("")))
    )

  def parameterDescriptionFromParametersStringList(
      parameters: Option[Set[String]]
  ): Option[Set[Parameter.Description]] =
    parameters.map(
      paramList => paramList.map(param => Parameter.Description(Parameter.Name(param), Parameter.DescriptionText("")))
    )

  /**
    * returns the regex to get information from markdown file for a rule
    *
    * @param ruleName Name of the rule to get information
    * @return
    */
  def ruleInfoRegex(ruleName: String) = s"## $ruleName([\\n\\S\\s\\w\\d][^##])*".r

  /**
    * Gets the tool version from the pattern.json file
    *
    * @param args
    * @return
    */
  def versionFromArgs(args: Array[String]): String =
    args.headOption
      .orElse {
        val sourceFile = io.Source.fromFile(codeNarcVersionFile)
        val resourceContent = sourceFile.getLines.mkString("")
        sourceFile.close()
        Some(resourceContent)
      }
      .getOrElse {
        throw new Exception("No version provided")
      }

  /**
    * Gets the documentation markdown name from the href element value
    *
    * @param href
    * @return
    */
  def extractMdFilenameFromHref(href: String): String =
    href.replaceAllLiterally("./", "").replaceAllLiterally(".html", "").split("#")(0) + ".md"

  /**
    * Get information about a rule from markdown file
    *
    * @param docsFolder   Documentation folder
    * @param ruleName     Name of the rule to get information about
    * @param ruleFilename The name of the markdown file where rule information is stored at.
    * @return
    */
  def getRuleExtendedInfoFromMarkdown(docsFolder: String, ruleName: String, ruleFilename: String): String = {
    val ruleInfoMarkdown = (docsFolder / ruleFilename).contentAsString

    val regexPattern = ruleInfoRegex(ruleName)
    val regexMatch = regexPattern.findFirstIn(ruleInfoMarkdown).getOrElse("")
    regexMatch.substring(regexMatch.indexOf(System.lineSeparator) + 1) // remove the title
  }

  /**
    * Get rule simplified description from codenarc-base-messages.properties file
    *
    * @param directory CodeNarc base directory
    * @param patternId rule/pattern name
    * @return Simplified Description
    */
  def getRuleBasicDescription(directory: String, patternId: String): String = {
    // This method trims the description to a max length of 500.
    // The basic description has a limit of 500 chars, so it is required to trim if it is longer than that
    // The trim is being done by removing a sentence from the end each time until it has less than 500 length.
    @tailrec
    def descriptionTrimAux(description: String): String = {
      if (description.length < 500) {
        description
      } else {
        descriptionTrimAux(description.substring(0, description.lastIndexOf(".")))
      }
    }

    val resourcesFolder = s"$directory/src/main/resources"
    val messagesFiles =
      File(resourcesFolder) / directory / "src" / "main" / "resources" / "codenarc-base-messages.properties"
    val messagesFileContent = messagesFiles.contentAsString

    val descriptionRegexFinder = s"$patternId.description=(.*)".r

    val description = descriptionRegexFinder
      .findFirstMatchIn(messagesFileContent)
      .map(_.group(1))
      .toList
      .headOption
      .getOrElse("")

    descriptionTrimAux(description)
  }

  /**
    * Get list of all CodeNarc's supported rules from git repos documentation
    *
    * @param toolVersion CodeNarc's version
    * @return
    */
  def listRulesSupportedFromGitDocumentation(toolVersion: String): Seq[RuleInformation] =
    GitHelper.withRepository(codeNarcGitRepository, toolVersion)(directory => {
      val documentationFolder = directory / "docs"

      val documentationStartingPoint = documentationFolder / codeNarcDocumentationStartingPoint
      val htmlListOfRules = MarkdownHelper.markdownToHtmlXml(documentationStartingPoint)

      // patterns list
      for {
        li <- htmlListOfRules \\ "li"
        a <- li \ "a"
      } yield {
        val ruleName = a.text
        val ruleInfoLocation = (a \ "@href").text

        val priority = rulePriority(ruleName)
        val parameters = ruleParameters(ruleName)
        val ruleInformationMdFile = extractMdFilenameFromHref(ruleInfoLocation)
        val ruleExtendedInfo =
          getRuleExtendedInfoFromMarkdown(documentationFolder.pathAsString, ruleName, ruleInformationMdFile)
        val ruleBasicDescription = getRuleBasicDescription(directory.toString, ruleName)

        RuleInformation(Pattern.Id(ruleName), ruleBasicDescription, ruleExtendedInfo, priority, parameters)
      }
    })

  /**
    * Get the tool specification with all required information (name, version, patterns list)
    *
    * @param patternsList List of patterns to be converted into Pattern.Specification list
    * @param toolName
    * @param toolVersion
    * @return
    */
  def toolSpec(patternsList: Seq[RuleInformation], toolName: String, toolVersion: String): Tool.Specification = {
    val patterns = patternsList.map(rule => {
      val rulePriority = rule.priority.getOrElse(defaultRulePriority)
      Pattern.Specification(
        rule.patternId,
        levelFromPriority(rulePriority.priority),
        categoryTypeFromCategory(rulePriority.priorityType),
        parameterSpecificationFromParametersStringList(rule.parameters)
      )
    })
    Tool.Specification(Tool.Name(toolName), Some(Tool.Version(toolVersion)), patterns.toSet)
  }

  /**
    * Get the list of Pattern.Description with each pattern required information
    *
    * @param patternsList
    * @return
    */
  def patternsDescription(patternsList: Seq[RuleInformation]): Seq[Pattern.Description] =
    patternsList.map(rule => {
      Pattern.Description(
        rule.patternId,
        Pattern.Title(rule.patternId.value),
        Some(Pattern.DescriptionText(rule.description)),
        None,
        parameterDescriptionFromParametersStringList(rule.parameters)
      )
    })

  private def isIntOrString(value: Field) =
    value.getType == classOf[Int] || value.getType == classOf[String]

  // value.getName.exists(_.isLower) -----> check if it is a constant (constants are all uppercase)
  //                                                   if it is not a constant, it should not be all uppercase
  private def isNotNameOrPriority(value: Field) =
    value.getName != "name" && value.getName != "priority" && value.getName.exists(_.isLower)

  private def getParametersFromType[T](classType: Class[T]) = {
    classType.getDeclaredFields
      .filter(x => {
        isIntOrString(x) && isNotNameOrPriority(x)
      })
      .map(_.getName)
      .toSet
  }

  // Check if rule is implemented by class type passed
  private def isRuleImplementedBy(ruleName: String, classType: Class[_ <: org.codenarc.rule.AbstractAstVisitorRule]) =
    Try(classType.getDeclaredConstructor().newInstance().getName == ruleName).getOrElse(false)

  def ruleParameters(ruleName: String): Option[Set[String]] = {
    val ruleClassImplementation = subTypes.find(isRuleImplementedBy(ruleName, _))

    ruleClassImplementation
      .map(getParametersFromType(_))
      .flatMap(x => if (x.isEmpty) None else Some(x))
  }

  /**
    * Get CodeNarc's rule priority
    *
    * @param ruleName Name of the rule
    * @return
    */
  def rulePriority(ruleName: String): Option[RulePriority] = {
    val ruleClassImplementation = subTypes.find(isRuleImplementedBy(ruleName, _))

    ruleClassImplementation.map { rci =>
      val instance = rci.getDeclaredConstructor().newInstance()
      val ruleCategory = rci.getPackage.getName.split("\\.").last
      val priority = instance.getPriority

      RulePriority(Some(priority.toString), Some(ruleCategory))
    }
  }

  def main(args: Array[String]): Unit = {
    val toolVersion: String = versionFromArgs(args)

    // get list of all rules supported by CodeNarc tool from its github repository
    val patternsList = listRulesSupportedFromGitDocumentation(toolVersion)

    // get tool pattern specifications
    val toolSpecification = toolSpec(patternsList, toolName, toolVersion)

    // get tool patterns descriptions
    val patternDescriptions = patternsDescription(patternsList)

    // convert pattern specifications into json
    val jsonSpecifications = Json.prettyPrint(Json.toJson(toolSpecification))
    // convert pattern descriptions into json
    val jsonDescriptions = Json.prettyPrint(Json.toJson(patternDescriptions))

    // create all files objects required to save documentation info
    val repoRoot = File(".")
    val docsFolderPath = repoRoot / "src" / "main" / "resources" / docsFolder
    val docsRoot = File(repoRoot, docsFolderPath.pathAsString)
    val patternsFile = File(docsRoot, patternsJsonFile)
    val descriptionsRoot = File(docsRoot, descriptionFolder)
    val descriptionsFile = File(descriptionsRoot, descriptionJsonFile)

    // write patterns.json and descriptions.json
    ResourceHelper.writeFile(patternsFile.path, jsonSpecifications)
    ResourceHelper.writeFile(descriptionsFile.path, jsonDescriptions)

    // write patterns extended descriptions to markdown files
    patternsList.foreach(description => {
      val descriptionMdFile = File(descriptionsRoot, s"${description.patternId}.md")
      ResourceHelper.writeFile(descriptionMdFile.path, description.descriptionExtended)
    })
  }
}

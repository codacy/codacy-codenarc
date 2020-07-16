package codacy.codenarc.docs

import java.lang.reflect.Field

import better.files._
import com.codacy.plugins.api.results.{Parameter, Pattern, Result, Tool}
import org.codenarc.rule.AbstractAstVisitorRule
import org.reflections.Reflections
import play.api.libs.json._

import scala.annotation.tailrec
import scala.jdk.CollectionConverters._
import scala.util.Try
import scala.util.matching.Regex

object DocGenerator {

  case class RulePriority(priority: String, priorityType: String)

  case class RulePatternInformation(
      patternSpecification: Pattern.Specification,
      patternDescription: Pattern.Description,
      descriptionMarkdown: String
  )

  private val toolName = "codenarc"

  private val codeNarcGitRepository = "github.com/CodeNarc/CodeNarc"
  private val patternsJsonFile = "patterns.json"
  private val descriptionJsonFile = "description.json"
  private val docsFolder = "docs"
  private val descriptionFolder = "description"

  private val codeNarcDocumentationStartingPoint = "codenarc-rule-index.md"

  private val reflections = new Reflections("org.codenarc.rule")
  private val subTypes = reflections.getSubTypesOf(classOf[org.codenarc.rule.AbstractAstVisitorRule]).asScala

  /**
    * Converts from CodeNarc's complexity to Codacy level
    */
  def levelFromPriority(priority: Option[String]): Result.Level.Value = priority match {
    case Some("1") => Result.Level.Err
    case Some("2") => Result.Level.Warn
    case Some("3") => Result.Level.Info
    case _ => Result.Level.Info
  }

  def categoryTypeFromPriorityType(priorityType: Option[String]): Pattern.Category.Value = priorityType match {
    case Some("security") => Pattern.Category.Security
    case Some("unused") => Pattern.Category.UnusedCode
    case _ => Pattern.Category.CodeStyle
  }

  def subcategoryForPatternId(patternId: String): Option[Pattern.Subcategory.Value] = patternId match {
    case "FileCreateTempFile" => Some(Pattern.Subcategory.InsecureModulesLibraries)
    case "InsecureRandom" => Some(Pattern.Subcategory.Cryptography)
    case "JavaIoPackageAccess" => Some(Pattern.Subcategory.FileAccess)
    case "NonFinalPublicField" | "PublicFinalizeMethod" => Some(Pattern.Subcategory.CommandInjection)
    case "SystemExit" | "UnsafeArrayDeclaration" => Some(Pattern.Subcategory.UnexpectedBehaviour)
    case "NonFinalSubclassOfSensitiveInterface" => Some(Pattern.Subcategory.MaliciousCode)
    case _ => None
  }

  /**
    * Convert from list of parameters to Parameter.Specification list
    */
  def parameterSpecificationFromParametersStringList(
      parameters: Option[Map[String, JsValue]]
  ): Option[Set[Parameter.Specification]] =
    parameters.map { paramList =>
      paramList.map {
        case (name, default) => Parameter.Specification(Parameter.Name(name), Parameter.Value(default))
      }.toSet
    }

  def parameterDescriptionFromParametersStringList(
      parameters: Option[Set[String]]
  ): Option[Set[Parameter.Description]] =
    parameters.map(
      paramList =>
        paramList.map(param => Parameter.Description(Parameter.Name(param), Parameter.DescriptionText(param)))
    )

  /**
    * returns the regex to get information from markdown file for a rule
    *
    * @param ruleName Name of the rule to get information
    * @return
    */
  def ruleInfoRegex(ruleName: String): Regex = s"(## $ruleName)([\\n\\S\\s\\w\\d][^##])*".r

  /**
    * Gets the tool version from the pattern.json file
    *
    * @param args
    * @return
    */
  def versionFromArgs(args: Array[String]): String =
    args.headOption.getOrElse(Versions.codenarcVersion)

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
    val markdown = regexMatch.linesIterator.toList match {
      case _ :: rest => rest.mkString(System.lineSeparator)
      case Nil => ""
    }

    MarkdownHelper.markdownToCommonmark(markdown)
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

    val messagesFiles = directory / "src" / "main" / "resources" / "codenarc-base-messages.properties"
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
  def listRulesSupportedFromGitDocumentation(toolVersion: String): Seq[RulePatternInformation] =
    GitHelper.withRepository(codeNarcGitRepository, toolVersion)(directory => {
      val documentationFolder = directory / "docs"

      val documentationStartingPoint = documentationFolder / codeNarcDocumentationStartingPoint
      val htmlListOfRules = MarkdownHelper.markdownToHtmlXml(documentationStartingPoint)

      // patterns list
      for {
        li <- htmlListOfRules \\ "li"
        a <- li \ "a"

        ruleName = a.text
        ruleInfoLocation = (a \ "@href").text

        rulePriority = getRulePriority(ruleName)
        parameters = getRuleParameters(ruleName)

        ruleInformationMdFile = extractMdFilenameFromHref(ruleInfoLocation)
        ruleExtendedInfo = getRuleExtendedInfoFromMarkdown(
          documentationFolder.pathAsString,
          ruleName,
          ruleInformationMdFile
        )
        ruleBasicDescription = getRuleBasicDescription(directory.toString, ruleName)

        patternSpec = Pattern.Specification(
          Pattern.Id(ruleName),
          levelFromPriority(rulePriority.map(_.priority)),
          categoryTypeFromPriorityType(rulePriority.map(_.priorityType)),
          subcategoryForPatternId(ruleName),
          parameterSpecificationFromParametersStringList(parameters)
        )

        patternDescription = Pattern.Description(
          Pattern.Id(ruleName),
          Pattern.Title(ruleName),
          Some(Pattern.DescriptionText(ruleBasicDescription)),
          None,
          parameterDescriptionFromParametersStringList(parameters.map(_.keySet))
        )
      } yield RulePatternInformation(patternSpec, patternDescription, ruleExtendedInfo)

    })

  /**
    * Get the tool specification with all required information (name, version, patterns list)
    *
    * @param patternsList List of patterns to be converted into Pattern.Specification list
    * @param toolName
    * @param toolVersion
    * @return
    */
  def toolSpec(patternsList: Seq[RulePatternInformation], toolName: String, toolVersion: String): Tool.Specification = {
    val patterns = patternsList.map(_.patternSpecification)
    Tool.Specification(Tool.Name(toolName), Some(Tool.Version(toolVersion)), patterns.toSet)
  }

  /**
    * Get the list of Pattern.Description with each pattern required information
    *
    * @param patternsList
    * @return
    */
  def patternsDescription(patternsList: Seq[RulePatternInformation]): Seq[Pattern.Description] =
    patternsList.map(_.patternDescription)

  private def hasValidType(value: Field) = {
    val t = value.getType()
    t == classOf[Int] || t == classOf[String] || t == classOf[Boolean]
  }

  private def hasValidName(value: Field) = value.getName() match {
    case "name" | "priority" | "__$stMC" => false
    case v if !v.exists(_.isLower) => false // Java constants don't have lower case characters
    case _ => true
  }

  private def getParameterDefault[T](classType: Class[T], value: Field): JsValue = {
    val instance = classType.newInstance()
    value.setAccessible(true)
    val t = value.getType()
    if (t == classOf[Int]) JsNumber(value.getInt(instance))
    else if (t == classOf[Boolean]) JsBoolean(value.getBoolean(instance))
    else if (t == classOf[String]) JsString(value.get(instance).asInstanceOf[String])
    else throw new Exception(s"No default for $classType / $value")
  }

  private def getParametersFromType[T](classType: Class[T]) = {
    classType.getDeclaredFields
      .filter(x => hasValidType(x) && hasValidName(x))
      .map(t => t.getName -> getParameterDefault(classType, t))
      .toMap
  }

  // Check if rule is implemented by class type passed
  private def isRuleImplementedBy(ruleName: String, classType: Class[_ <: org.codenarc.rule.AbstractAstVisitorRule]) =
    Try(classType.getDeclaredConstructor().newInstance().getName == ruleName).getOrElse(false)

  def ruleImplementationClass(ruleName: String): Option[Class[_ <: AbstractAstVisitorRule]] =
    subTypes.find(isRuleImplementedBy(ruleName, _))

  def getRuleParameters(ruleName: String): Option[Map[String, JsValue]] = {
    val ruleClassImplementation = ruleImplementationClass(ruleName)

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
  def getRulePriority(ruleName: String): Option[RulePriority] = {
    val ruleClassImplementation = ruleImplementationClass(ruleName)

    ruleClassImplementation.map { rci =>
      val instance = rci.getDeclaredConstructor().newInstance()
      val ruleCategory = rci.getPackage.getName.split("\\.").last
      val priority = instance.getPriority

      RulePriority(priority.toString, ruleCategory)
    }
  }

  def writePatternsExtendedInfoToMarkdown(patternsList: Seq[RulePatternInformation], descriptionsRoot: File): Unit =
    patternsList.foreach(description => {
      val patternId = description.patternSpecification.patternId.value
      val descriptionMdFile = File(descriptionsRoot, s"$patternId.md")
      ResourceHelper.writeFile(descriptionMdFile.path, description.descriptionMarkdown)
    })

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

    writePatternsExtendedInfoToMarkdown(patternsList, descriptionsRoot)
  }
}

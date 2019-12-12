package codacy.codenarc.docs

import better.files.File
import com.codacy.plugins.api.results.{Pattern, Result, Tool}
import play.api.libs.json.{JsObject, Json}

import scala.util.matching.Regex


object DocGenerator {
  private val toolName = "codenarc"

  private val codeNarcGitRepository = "github.com/CodeNarc/CodeNarc"
  private val patternsJsonFile = "patterns.json"
  private val descriptionJsonFile = "description.json"
  private val docsFolder = "docs"
  private val descriptionFolder = "description"

  private val codeNarcDocumentationStartingPoint = "codenarc-rule-index.md"

  case class PatternInformation(patternId: Pattern.Id, description: String, descriptionExtended: String = "")

  /**
   * returns the regex to get information from markdown file for a rule
   *
   * @param ruleName Name of the rule to get information
   * @return
   */
  def ruleInfoRegex(ruleName: String) = new Regex("## " + ruleName + "([\\n\\S\\s\\w\\d][^##])*")

  /**
   * Gets the tool version from the pattern.json file
   *
   * @param args
   * @return
   */
  def versionFromArgs(args: Array[String]): String = args.headOption
    .orElse {
      ResourceHelper
        .getResourceContent(s"$docsFolder/$patternsJsonFile")
        .toOption
        .flatMap { lines =>
          Json.parse(lines.mkString("\n")).as[JsObject].\("version").asOpt[String]
        }
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
    val ruleInfoMarkdown = File(s"$docsFolder$ruleFilename").contentAsString

    val regexPattern = ruleInfoRegex(ruleName)
    val regexMatch = regexPattern.findFirstIn(ruleInfoMarkdown).getOrElse("")
    regexMatch.substring(regexMatch.indexOf('\n') + 1) // remove the title
  }

  /**
   * Get rule simplified description from codenarc-base-messages.properties file
   *
   * @param directory CodeNarc base directory
   * @param patternId rule/pattern name
   * @return Simplified Description
   */
  def getRuleBasicDescription(directory: String, patternId: String): Option[String] = {
    val resourcesFolder = s"$directory/src/main/resources"
    val messagesFileContent = File(s"$resourcesFolder/codenarc-base-messages.properties").contentAsString

    val descriptionRegexFinder = new Regex(s"$patternId.description=(.*)")

    (descriptionRegexFinder.findAllIn(messagesFileContent).matchData map {
      m => m.group(1)
    }).toList.headOption
  }


  /**
   * Get list of all CodeNarc's supported rules from git repos documentation
   * @param toolVersion CodeNarc's version
   * @return
   */
  def listRulesSupportedFromGitDocumentation(toolVersion: String): Seq[PatternInformation] = GitHelper.withRepository(codeNarcGitRepository, toolVersion)(directory => {
    val documentationFolder = s"$directory/docs/"

    val documentationStartingPoint = File(s"$documentationFolder$codeNarcDocumentationStartingPoint")
    val htmlListOfRules = MarkdownHelper.markdownToHtmlXml(documentationStartingPoint)

    // patterns list
    for {
      li <- htmlListOfRules \\ "li"
      a <- li \ "a"
    } yield {
      val ruleName = a.text
      val ruleInfoLocation = (a \ "@href").text

      val ruleInformationMdFile = extractMdFilenameFromHref(ruleInfoLocation)
      val ruleExtendedInfo = getRuleExtendedInfoFromMarkdown(documentationFolder, ruleName, ruleInformationMdFile)
      val ruleBasicDescription = getRuleBasicDescription(directory.toString, ruleName)

      PatternInformation(Pattern.Id(ruleName), ruleBasicDescription.getOrElse(""), ruleExtendedInfo)
    }
  })

  /**
   * Get the tool specification with all required information (name, version, patterns list)
   * @param patternsList List of patterns to be converted into Pattern.Specification list
   * @param toolName
   * @param toolVersion
   * @return
   */
  def toolSpec(patternsList: Seq[PatternInformation], toolName: String, toolVersion: String): Tool.Specification = {
    val patterns = patternsList.map(rule => {
      Pattern.Specification(rule.patternId, Result.Level.Info, Pattern.Category.CodeStyle, None)
    })
    Tool.Specification(Tool.Name(toolName), Some(Tool.Version(toolVersion)), patterns.toSet)
  }

  /**
   * Get the list of Pattern.Description with each pattern required information
   * @param patternsList
   * @return
   */
  def patternsDescription(patternsList: Seq[PatternInformation]): Seq[Pattern.Description] = patternsList.map(rule => {
    Pattern.Description(rule.patternId, Pattern.Title(rule.patternId.value), Some(Pattern.DescriptionText(rule.description)), None, None)
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
    val repoRoot = new java.io.File(".")
    val docsRoot = new java.io.File(repoRoot, s"src/main/resources/$docsFolder")
    val patternsFile = new java.io.File(docsRoot, patternsJsonFile)
    val descriptionsRoot = new java.io.File(docsRoot, descriptionFolder)
    val descriptionsFile = new java.io.File(descriptionsRoot, descriptionJsonFile)

    // write patterns.json and descriptions.json
    ResourceHelper.writeFile(patternsFile.toPath, jsonSpecifications)
    ResourceHelper.writeFile(descriptionsFile.toPath, jsonDescriptions)

    // write patterns extended descriptions to markdown files
    patternsList.foreach(description => {
      val descriptionMdFile = new java.io.File(descriptionsRoot, description.patternId + ".md")
      ResourceHelper.writeFile(descriptionMdFile.toPath, description.descriptionExtended)
    })
  }
}

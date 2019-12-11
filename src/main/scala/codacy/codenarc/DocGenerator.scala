package codacy.codenarc

import java.io.IOException

import com.codacy.plugins.api.results.{Pattern, Result, Tool}
import play.api.libs.json.{JsObject, Json}

import better.files.File
import laika.api.Transformer
import laika.format.{HTML, Markdown}
import laika.parse.markup.DocumentParser.ParserError

import scala.sys.process._
import scala.util.matching.Regex
import scala.xml.{Elem, XML}

object DocGenerator {
  private val toolName = "codenarc"
  private val codeNarcGitRepository = "github.com/CodeNarc/CodeNarc"
  private val patternsJsonFile = "patterns.json"
  private val descriptionJsonFile = "description.json"
  private val docsFolder = "docs"
  private val descriptionFolder = "description"

  private val codeNarcDocumentationStartingPoint = "codenarc-rule-index.md"

  private case class PatternDescription(patternId: Pattern.Id, description: String)

  private def withRepository[T](version: String)(block: File => T): T = {
    // Disposes temporary directory at the end
    val res = for {
      directory <- File.temporaryDirectory("codenarc")
    } yield {
      s"git clone git://$codeNarcGitRepository --depth 1 -b v$version $directory".!!
      block(directory)
    }
    res.get
  }

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

  def markdownToHtml(markdownString: String): String = {
    val transformer = Transformer
      .from(Markdown)
      .to(HTML)
      .build

    val res: Either[ParserError, String] = transformer.transform(markdownString)
    res match {
      case Left(e) => throw new IOException(s"Error parsing markdown: $e")
      case Right(html) =>
        val htmlContent = html.replaceAllLiterally("<br>", "").replaceAllLiterally("<hr>", "")
        s"<html>$htmlContent</html>"
    }
  }

  def ruleInfoRegex(ruleName: String) = new Regex("## " + ruleName + "([\\n\\S\\s\\w\\d][^##])*")

  def extractMdFilenameFromHref(href: String): String =
    href.replaceAllLiterally("./", "").replaceAllLiterally(".html", "").split("#")(0) + ".md"

  private def codeNarcDocsFolder(directory: String) = s"$directory/docs/"

  def parseRuleInfo(directory: String, ruleName: String, ruleFilename: String): String = {
    val docsFolder = codeNarcDocsFolder(directory)
    val ruleInfoMarkdown = File(s"$docsFolder$ruleFilename").contentAsString

    val regexPattern = ruleInfoRegex(ruleName)
    val regexMatch = regexPattern.findFirstIn(ruleInfoMarkdown).getOrElse("")
    regexMatch.substring(regexMatch.indexOf('\n') + 1) // remove the title
  }

  def markdownToHtmlXml(markdownFile: File): Elem = {
    val ruleIndexHtmlString = markdownToHtml(markdownFile.contentAsString)

    XML.loadString(ruleIndexHtmlString)
  }

  def main(args: Array[String]): Unit = {
    val toolVersion: String = versionFromArgs(args)

    // get list of all rules supported by CodeNarc tool from its github repository
    val patternsList = withRepository(toolVersion)(directory => {
      val documentationFolder = codeNarcDocsFolder(directory.toString)
      val documentationStartingPoint = File(s"$documentationFolder$codeNarcDocumentationStartingPoint")
      val htmlListOfRules = markdownToHtmlXml(documentationStartingPoint)

      // patterns list
      for {
        li <- htmlListOfRules \\ "li"
        a <- li \ "a"
      } yield {
        val ruleName = a.text
        val ruleInfoLocation = (a \ "@href").text

        val ruleInformationMdFile = extractMdFilenameFromHref(ruleInfoLocation)
        val ruleInfo = parseRuleInfo(directory.toString, ruleName, ruleInformationMdFile)

        PatternDescription(Pattern.Id(ruleName), ruleInfo)
      }
    })

    // get tool pattern specifications
    val patternSpecifications = patternsList.map(rule => {
      Pattern.Specification(rule.patternId, Result.Level.Info, Pattern.Category.CodeStyle, None)
    })
    val toolSpecification = Tool.Specification(Tool.Name(toolName), Some(Tool.Version(toolVersion)), patternSpecifications.toSet)

    // get tool patterns descriptions
    val patternDescriptions = patternsList.map(rule => {
      Pattern.Description(rule.patternId, Pattern.Title(rule.patternId.value), None, None, None)
    })

    // convert pattern specifications into json
    val jsonSpecifications = Json.prettyPrint(Json.toJson(toolSpecification))
    // convert pattern descriptions into json
    val jsonDescriptions = Json.prettyPrint(Json.toJson(patternDescriptions))

    // create all files objects
    val repoRoot = new java.io.File(".")
    val docsRoot = new java.io.File(repoRoot, s"src/main/resources/$docsFolder")
    val patternsFile = new java.io.File(docsRoot, patternsJsonFile)
    val descriptionsRoot = new java.io.File(docsRoot, descriptionFolder)
    val descriptionsFile = new java.io.File(descriptionsRoot, descriptionJsonFile)

    // write patterns.json and descriptions.json
    ResourceHelper.writeFile(patternsFile.toPath, jsonSpecifications)
    ResourceHelper.writeFile(descriptionsFile.toPath, jsonDescriptions)

    // write patterns descriptions to markdown files
    patternsList.foreach(description => {
      val descriptionMdFile = new java.io.File(descriptionsRoot, description.patternId + ".md")
      ResourceHelper.writeFile(descriptionMdFile.toPath, description.description)
    })
  }
}

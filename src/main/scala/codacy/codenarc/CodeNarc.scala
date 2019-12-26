package codacy.codenarc

import better.files.File

import com.codacy.plugins
import com.codacy.plugins.api.{Options, Source}
import com.codacy.plugins.api.results.{Result, Tool}
import com.codacy.tools.scala.seed.utils.FileHelper.findConfigurationFile

import java.io.{FileOutputStream, PrintStream}
import java.nio.file.{Path, Paths}

import org.codenarc.CodeNarcRunner
import org.codenarc.analyzer.FilesystemSourceAnalyzer

import play.api.libs.json.{JsString, JsValue}

import scala.util.Try

object CodeNarc extends Tool {
  private lazy val configFileNames = Set(".codenarcrc")

  private val codeNarcDefaultConfigPath = "default_config/default.txt"

  private[codenarc] case class Pattern(id: String, parameters: Set[PatternParameter])
  private[codenarc] case class PatternParameter(name: String, value: JsValue)

  /**
    * Check if source code repos contains CodeNarc's configuration file.
    * @param source
    * @return Option with configuration file path
    */
  private def sourceCodeRepositoryContainsConfigFile(source: Source.Directory): Option[Path] =
    findConfigurationFile(Paths.get(source.path), configFileNames)

  private def paramValueAsString(paramValue: JsValue): String =
    paramValue match {
      case JsString(value) => s"'$value'"
      case v => v.toString
    }

  private def patternParamConfigString(param: PatternParameter): String = {
    val value = paramValueAsString(param.value)
    s"${param.name} = $value"
  }

  private def patternConfigurationString(pattern: Pattern): String =
    if (pattern.parameters.isEmpty) {
      pattern.id
    } else {
      val parameters = pattern.parameters
        .map(param => patternParamConfigString(param))
        .mkString("\n")

      s"""${pattern.id} {
         |$parameters
         |}""".stripMargin
    }

  /**
    * Get the configuration file content from list of patterns to analyze
    * @param patterns
    * @return
    */
  def ruleFileContentFromPatterns(patterns: List[Pattern]): String = {
    val patternsAsString = patterns
      .map(patternConfigurationString)
      .mkString("\n")

    s"""ruleset {
       |$patternsAsString
       |}""".stripMargin
  }

  private def generateConfigurationFile(patterns: List[Pattern]): File =
    saveConfigurationFile(ruleFileContentFromPatterns(patterns))

  private def saveConfigurationFile(content: String): File =
    File
      .newTemporaryFile("codacycodenarc", ".txt")
      .write(content)

  def patternDefinitionToCodeNarcPattern(pattern: plugins.api.results.Pattern.Definition): Pattern = {
    val parameters: Set[PatternParameter] =
      pattern.parameters
        .map(
          optionParamSet =>
            optionParamSet.map(param => {
              val valueAsJsValue: JsValue = param.value
              PatternParameter(param.name.value, valueAsJsValue)
            })
        )
        .getOrElse(Set())

    Pattern(pattern.patternId.value, parameters)
  }

  def getConfigurationFilePath(
      configuration: Option[List[plugins.api.results.Pattern.Definition]],
      source: Source.Directory
  ): Path =
    configuration
      .map { config =>
        val codeNarcPatterns = config.map(patternDefinitionToCodeNarcPattern)

        generateConfigurationFile(codeNarcPatterns).path
      }
      .orElse {
        sourceCodeRepositoryContainsConfigFile(source)
      }
      .getOrElse {
        val defaultConfigContent = scala.io.Source.fromResource(codeNarcDefaultConfigPath).mkString
        saveConfigurationFile(defaultConfigContent).path
      }

  def filesToAnalyse(filesOpt: Option[Set[Source.File]]): String = filesOpt match {
    case Some(filesList) => filesList.mkString(",")
    case None => "**/*.groovy"
  }

  def ruleConfigFile(configurationFilePath: Path): String = s"file:${configurationFilePath.toString}"

  /**
    * CodeNarc makes some println's which we need to ignore
    */
  def codeNarcPrintlnIgnore(): Unit =
    System.setOut(new PrintStream(new FileOutputStream(File.newTemporaryFile("out").toJava)))

  def run(
      source: Source.Directory,
      configurationFilePath: Path,
      filesOpt: Option[Set[Source.File]]
  ): List[CodeNarcOutput.CodeNarcOutput] = {
    val filesToInclude = filesToAnalyse(filesOpt)
    val ruleConfiguration = ruleConfigFile(configurationFilePath)

    codeNarcPrintlnIgnore()

    val result = runAnalysis(source.path, ruleConfiguration, filesToInclude)

    CodeNarcOutput.parseResult(result)
  }

  private def runAnalysis(sourceDir: String, rulesList: String, includesFiles: String) = {
    val codeNarcRunner = new CodeNarcRunner()
    codeNarcRunner.setRuleSetFiles(rulesList)
    val sourceAnalyzer = new FilesystemSourceAnalyzer
    sourceAnalyzer.setBaseDirectory(sourceDir)
    sourceAnalyzer.setIncludes(includesFiles)

    codeNarcRunner.setSourceAnalyzer(sourceAnalyzer)

    codeNarcRunner.execute()
  }

  /**
    * Convert CodeNarcOutput object into Result.Issue object
    *
    * @param codeNarcOutput List of CodeNarcOutput
    * @return List of Result.Issue
    */
  def codeNarcOutputToResult(codeNarcOutput: CodeNarcOutput.CodeNarcOutput): Result.Issue = Result.Issue(
    Source.File(codeNarcOutput.file),
    Result.Message(codeNarcOutput.message),
    plugins.api.results.Pattern.Id(codeNarcOutput.ruleName),
    Source.Line(codeNarcOutput.line)
  )

  override def apply(
      source: Source.Directory,
      configuration: Option[List[plugins.api.results.Pattern.Definition]],
      files: Option[Set[Source.File]],
      options: Map[Options.Key, Options.Value]
  )(implicit specification: Tool.Specification): Try[List[Result]] = {
    Try {
      val configurationFileLocation = getConfigurationFilePath(configuration, source)
      val codeNarcResult = run(source, configurationFileLocation, files)
      val toolResult = codeNarcResult.map(codeNarcOutputToResult)

      toolResult
    }
  }
}

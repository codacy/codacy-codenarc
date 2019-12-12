package codacy.codenarc

import java.io.{FileOutputStream, PrintStream}

import com.codacy.plugins.api.{Options, Source}
import com.codacy.plugins.api.results.{Pattern, Result, Tool}
import com.codacy.tools.scala.seed.utils.FileHelper._
import java.nio.file.{Path, Paths}

import better.files.File

import scala.util.Try

object CodeNarc extends Tool {
  private lazy val configFileNames = Set(
    ".codenarcrc"
  )

  private val codeNarcResultFileType = "xml"
  private val codeNarcResultFilename = s"CodeNarcResult.$codeNarcResultFileType"

  case class PatternTuple(id: String, parameter: String)

  def sourceCodeRepositoryContainsConfigFile(source: Source.Directory): Option[Path] = {
    findConfigurationFile(Paths.get(source.path), configFileNames)
  }

  def ruleFileContentFromPatterns(patterns: List[PatternTuple]): String = s"ruleset {\n${patterns.map(_.id).mkString("\n")}\n}"

  def generateConfigurationFile(patterns: List[PatternTuple]): File = generateConfigurationFile(ruleFileContentFromPatterns(patterns))

  def generateConfigurationFile(content: String): File = File
    .newTemporaryFile("codacycodenarc", ".txt")
    .write(content)

  def getConfigurationFilePath(configuration: Option[List[Pattern.Definition]], source: Source.Directory): Path =
    configuration.map {
      config =>
        // generate configuration file from patterns passed
        val patterns = config.map { pattern =>
          val parameter = pattern.parameters.flatMap(_.headOption.map {
            param => param.value.toString
          }).getOrElse("")

          PatternTuple(pattern.patternId.value, parameter)
        }
        // generate configuration file as temp file and return its path
        generateConfigurationFile(patterns).path
    }.orElse {
      // otherwise, check if configuration is inside source dir
      sourceCodeRepositoryContainsConfigFile(source)
    }.getOrElse {
      val defaultConfigContent = scala.io.Source.fromResource("docs/default_config/default.txt").mkString
      generateConfigurationFile(defaultConfigContent).path
    }

  def filesToAnalyse(filesOpt: Option[Set[Source.File]]) = filesOpt match {
    case Some(filesList) => "-includes=" + filesList.mkString(",")
    case None => "-includes=**/*.groovy"
  }

  def run(source: Source.Directory, configurationFilePath: Path, filesOpt: Option[Set[Source.File]]) = {
    // 1. Set ruleset file to use when calling the tool
    val ruleConfigParam = "-rulesetfiles=file:" + configurationFilePath.toString

    // files to include in analysis
    val filesToInclude = filesToAnalyse(filesOpt)

    // required so that codenarc printlns dont appear on console
    System.setOut(new PrintStream(new FileOutputStream(File.newTemporaryFile("out").toJava)))

    // 2. Call CodeNarc tool
    org.codenarc.CodeNarc.main(ruleConfigParam, filesToInclude, "-basedir=" + source.path, s"-report=$codeNarcResultFileType:$codeNarcResultFilename")

    // 3. Parse CodeNarc tool XML result
    val resultFile = File(codeNarcResultFilename)
    CodeNarcOutput.parseResult(resultFile)
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
    Pattern.Id(codeNarcOutput.ruleName),
    Source.Line(codeNarcOutput.line)
  )

  /**
   * Convert list of CodeNarcOutput objects into list of Result.Issue objects
   *
   * @param codeNarcOutput List of CodeNarcOutput
   * @return List of Result.Issue
   */
  def codeNarcOutputToToolResult(codeNarcOutput: List[CodeNarcOutput.CodeNarcOutput]): List[Result.Issue] = codeNarcOutput.map(codeNarcOutputToResult)

  override def apply(
                      source: Source.Directory,
                      configuration: Option[List[Pattern.Definition]],
                      files: Option[Set[Source.File]],
                      options: Map[Options.Key, Options.Value])(implicit specification: Tool.Specification): Try[List[Result]] = {
    Try {
      val configurationFileLocation = getConfigurationFilePath(configuration, source)
      val codeNarcResult = run(source, configurationFileLocation, files)
      val toolResult = codeNarcOutputToToolResult(codeNarcResult)

      toolResult
    }
  }
}


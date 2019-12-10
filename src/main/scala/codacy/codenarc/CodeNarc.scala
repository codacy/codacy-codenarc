package codacy.codenarc

import com.codacy.plugins.api.{Options, Source}
import com.codacy.plugins.api.results.{Pattern, Result, Tool}
import com.codacy.tools.scala.seed.utils.FileHelper._
import java.nio.file.{Path, Paths}

import better.files.File

import scala.util.{Try}

object CodeNarc extends Tool {
  private lazy val configFileNames = Set(
    ".codenarcrc"
  )

  case class PatternTuple(id: String, parameter: String)

  def sourceCodeRepositoryContainsConfigFile(source: Source.Directory): Option[Path] = {
    findConfigurationFile(Paths.get(source.path), configFileNames)
  }

  def ruleFileContentFromPatterns(patterns: List[PatternTuple]): String = s"ruleset {\n${patterns.map(_.id).mkString("\n")}\n}"

  def generateConfigurationFile(patterns: List[PatternTuple]): File = File
    .newTemporaryFile("codacy-codenarc", ".txt")
    .write(ruleFileContentFromPatterns(patterns))

  def getConfigurationFilePath(configuration: Option[List[Pattern.Definition]], source: Source.Directory): Option[Path] =
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
    }

  override def apply(
                      source: Source.Directory,
                      configuration: Option[List[Pattern.Definition]],
                      files: Option[Set[Source.File]],
                      options: Map[Options.Key, Options.Value])(implicit specification: Tool.Specification): Try[List[Result]] = {
    getConfigurationFilePath(configuration, source)
    ???
  }


}


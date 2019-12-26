package codacy.codenarc.docs

import java.net.URL
import java.nio.file.Path

import better.files.File

import scala.io.Source

import scala.util.{Failure, Properties, Try, Using}

object ResourceHelper {

  def getResourceContent(path: String): Try[List[String]] = {
    Option(getClass.getClassLoader.getResource(path))
      .map { url =>
        getResourceContent(url)
      }
      .getOrElse {
        Failure(new Exception("The path provided was not found"))
      }
  }

  private def getResourceContent(url: URL): Try[List[String]] = {
    Try {
      Using.resource(Source.fromURL(url)) { bufferedSource =>
        bufferedSource.mkString
          .split(Properties.lineSeparator)
          .toList
      }
    }
  }

  def writeFile(path: Path, content: String): Try[Path] = {
    Try {
      File(path)
        .createIfNotExists()
        .overwrite(content)
        .path
    }
  }
}

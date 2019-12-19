package codacy.codenarc.docs

import java.net.URL
import java.nio.charset.{CodingErrorAction, StandardCharsets}
import java.nio.file.{Files, Path, StandardOpenOption}

import scala.io.{Codec, Source}
import scala.util.{Failure, Properties, Try}

object ResourceHelper {

  implicit val codec: Codec =
    Codec.UTF8
      .onMalformedInput(CodingErrorAction.REPLACE)
      .onUnmappableCharacter(CodingErrorAction.REPLACE)

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
    Try(url.openStream()).flatMap { stream =>
      val lines = Try {
        Source
          .fromInputStream(stream)
          .mkString
          .split(Properties.lineSeparator)
          .toList
      }

      Try(stream.close())

      lines
    }
  }

  def writeFile(path: Path, content: String): Try[Path] = {
    Try(
      Files.write(
        path,
        content.getBytes(StandardCharsets.UTF_8),
        StandardOpenOption.CREATE,
        StandardOpenOption.TRUNCATE_EXISTING,
        StandardOpenOption.WRITE
      )
    )
  }

}

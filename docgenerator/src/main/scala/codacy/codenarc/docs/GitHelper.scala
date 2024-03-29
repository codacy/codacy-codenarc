package codacy.codenarc.docs

import better.files.File
import scala.sys.process._

object GitHelper {

  def withRepository[T](repository: String, version: String)(block: File => T): T = {
    val res = for {
      directory <- File.temporaryDirectory("codenarcRepository")
    } yield {
      s"git clone https://$repository --depth 1 -b v$version $directory".!!
      val result = block(directory)
      result
    }
    res.get
  }
}

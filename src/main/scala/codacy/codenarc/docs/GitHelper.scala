package codacy.codenarc.docs

import better.files.File
import scala.sys.process._

object GitHelper {
  def withRepository[T](repository: String, version: String)(block: File => T): T = {
    // Disposes temporary directory at the end
    val res = for {
      directory <- File.temporaryDirectory("tmp_repository")
    } yield {
      s"git clone git://$repository --depth 1 -b v$version $directory".!!
      block(directory)
    }
    res.get
  }
}

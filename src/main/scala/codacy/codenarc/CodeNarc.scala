package codacy.codenarc

import com.codacy.plugins.api.{Options, Source}
import com.codacy.plugins.api.results.{Pattern, Result, Tool}

import scala.util.Try

object CodeNarc extends Tool {
  override def apply(
                      source: Source.Directory,
                      configuration: Option[List[Pattern.Definition]],
                      files: Option[Set[Source.File]],
                      options: Map[Options.Key, Options.Value])(implicit specification: Tool.Specification): Try[List[Result]] = ???
}


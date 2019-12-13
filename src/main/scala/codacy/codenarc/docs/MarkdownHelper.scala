package codacy.codenarc.docs

import java.io.IOException

import better.files.File
import laika.api.Transformer
import laika.format.{HTML, Markdown}
import laika.parse.markup.DocumentParser.ParserError

import scala.xml.{Elem, XML}

object MarkdownHelper {

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

  def markdownToHtmlXml(markdownFile: File): Elem = {
    val ruleIndexHtmlString = markdownToHtml(markdownFile.contentAsString)

    XML.loadString(ruleIndexHtmlString)
  }
}

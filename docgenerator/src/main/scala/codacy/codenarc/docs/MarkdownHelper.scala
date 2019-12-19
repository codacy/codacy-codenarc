package codacy.codenarc.docs

import better.files.File

import scala.xml.{Elem, XML}
import sys.process._

object MarkdownHelper {

  def markdownToHtml(markdownFile: File): String = {
    val html = Seq("pandoc", "-f", "markdown", "-t", "html", markdownFile.path.toString).!!
    val htmlContent = html.replaceAllLiterally("<br>", "").replaceAllLiterally("<hr>", "")
    s"<html>$htmlContent</html>"
  }

  def markdownToHtmlXml(markdownFile: File): Elem = {
    val ruleIndexHtmlString = markdownToHtml(markdownFile)

    XML.loadString(ruleIndexHtmlString)
  }
}

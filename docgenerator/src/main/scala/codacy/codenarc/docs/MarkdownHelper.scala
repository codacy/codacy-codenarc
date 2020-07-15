package codacy.codenarc.docs

import better.files.File

import scala.xml.Elem
import sys.process._

object MarkdownHelper {

  def markdownToHtml(markdownFile: File): String = {
    val htmlContent = Seq("pandoc", "-f", "markdown", "-t", "html", markdownFile.path.toString).!!
    s"<div>$htmlContent</div>"
  }

  def markdownToCommonmark(markdown: String): String = {
    val input = new java.io.ByteArrayInputStream(markdown.getBytes("UTF-8"))
    Seq("pandoc", "-f", "markdown", "-t", "commonmark").#<(input).!!
  }

  def markdownToHtmlXml(markdownFile: File): Elem = {
    val ruleIndexHtmlString = markdownToHtml(markdownFile)

    XML.loadString(ruleIndexHtmlString)
  }
}

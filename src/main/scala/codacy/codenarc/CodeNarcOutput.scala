package codacy.codenarc

import better.files.File

import scala.xml.{Elem, XML}

object CodeNarcOutput {

  case class CodeNarcOutput(file: String, message: String, ruleName: String, line: Int)

  def loadXMLResult(resultFile: File): Elem = XML.loadString(resultFile.contentAsString)

  def parseResult(resultFile: File): List[CodeNarcOutput] = {
    val xmlResult = loadXMLResult(resultFile)
    parseXmlResult(xmlResult)
  }

  private def parseXmlResult(resultXml: Elem): List[CodeNarcOutput] = ???
}
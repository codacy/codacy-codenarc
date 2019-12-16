package codacy.codenarc

import better.files.File

import scala.util.Try
import scala.xml.{Elem, Node, XML}

object CodeNarcOutput {

  private val packageNode = "Package"
  private val packagePathValue = "@path"
  private val fileNode = "File"
  private val fileNameValue = "@name"
  private val violationNode = "Violation"
  private val violationRuleNameValue = "@ruleName"
  private val violationMessageNode = "Message"
  private val violationLineNumber = "@lineNumber"

  case class CodeNarcOutput(file: String, message: String, ruleName: String, line: Int)

  case class CodeNarcViolation(ruleName: String, message: String, line: Int)

  def parseResult(resultFile: File): List[CodeNarcOutput] = {
    val xmlResult = XML.loadFile(resultFile.toJava)
    parseXmlResult(xmlResult).toList
  }

  private def violationRuleName(violationNode: Node) = (violationNode \ violationRuleNameValue).text

  private def violationMessage(violationNode: Node) = (violationNode \ violationMessageNode).text

  private def violationLine(violationNode: Node) =
    Try((violationNode \ violationLineNumber).toString.toInt).getOrElse(0)

  private def getViolationInfo(violation: Node): CodeNarcViolation =
    CodeNarcViolation(violationRuleName(violation), violationMessage(violation), violationLine(violation))

  private def getFilenameFromFileNode(fileNode: Node): String = (fileNode \ fileNameValue).text

  private def getFilenameWithPackageFromFileNode(fileNode: Node, packagePath: String): String = {
    val filename = getFilenameFromFileNode(fileNode)
    s"$packagePath/${filename}"
  }

  private def getPackagePathFromFileNode(packageNode: Node) = (packageNode \ packagePathValue).text

  private def getListOfPackages(resultXml: Elem) = resultXml \ packageNode

  private def getListOfFilesInsidePackage(pkg: Node) = pkg \ fileNode

  private def getListOfViolationsInsideFile(file: Node) = file \ violationNode

  def parseXmlResult(resultXml: Elem): Seq[CodeNarcOutput] =
    for {
      pkg <- getListOfPackages(resultXml)
      file <- getListOfFilesInsidePackage(pkg)
      violation <- getListOfViolationsInsideFile(file)
    } yield {
      val packagePath = getPackagePathFromFileNode(pkg)
      val filename = getFilenameWithPackageFromFileNode(file, packagePath)
      val violationInfo = getViolationInfo(violation)

      CodeNarcOutput(filename, violationInfo.message, violationInfo.ruleName, violationInfo.line)
    }
}

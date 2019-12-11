package codacy.codenarc

import better.files.File

import scala.util.Try
import scala.xml.{Elem, XML, Node}

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

  def loadXMLResult(resultFile: File): Elem = XML.loadString(resultFile.contentAsString)

  def parseResult(resultFile: File): List[CodeNarcOutput] = {
    val xmlResult = loadXMLResult(resultFile)
    parseXmlResult(xmlResult).toList
  }

  private def violationRuleName(violationNode: Node) = (violationNode \ violationRuleNameValue).text

  private def violationMessage(violationNode: Node) = (violationNode \ violationMessageNode).text

  private def violationLine(violationNode: Node) = Try((violationNode \ violationLineNumber).toString.toInt).getOrElse(0)

  private def getViolationInfo(violation: Node): CodeNarcViolation = CodeNarcViolation(violationRuleName(violation), violationMessage(violation), violationLine(violation))

  private def getFilenameFromFileNode(fileNode: Node, packagePath: String) = s"$packagePath/${(fileNode \ fileNameValue).text}"

  private def getPackagePathFromFileNode(packageNode: Node) = (packageNode \ packagePathValue).text

  private def getListOfPackages(resultXml: Elem) = resultXml \ packageNode

  private def getListOfFilesInsidePackage(pkg: Node) = pkg \ fileNode

  private def getListOfViolationsInsideFile(file: Node) = file \ violationNode

  def parseXmlResult(resultXml: Elem): Seq[CodeNarcOutput] =
    for {
      // go over all packages
      pkg <- getListOfPackages(resultXml)
      // go over files inside each package
      file <- getListOfFilesInsidePackage(pkg)
      // get all violations for each file
      violation <- getListOfViolationsInsideFile(file)
    } yield {
      val packagePath = getPackagePathFromFileNode(pkg)
      val filename = getFilenameFromFileNode(file, packagePath)
      val violationInfo = getViolationInfo(violation)

      CodeNarcOutput(filename, violationInfo.message, violationInfo.ruleName, violationInfo.line)
    }
}
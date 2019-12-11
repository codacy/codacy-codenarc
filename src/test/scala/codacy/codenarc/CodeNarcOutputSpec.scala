package codacy.codenarc

import better.files.File
import org.scalatest._

import scala.xml.XML

class CodeNarcOutputSpec extends WordSpec with BeforeAndAfterAll {
  val codeNarcXmlResultResource = getClass.getResource("/codeNarcXmlResult.xml")
  val codeNarcXmlResult = XML.load(codeNarcXmlResultResource)

  val resultFile = File.newTemporaryFile("result", ".xml").write(codeNarcXmlResult.toString)
  
  override def afterAll() {
    resultFile.delete()
  }

  "parsing the XML result file" should {
    "return the XML document" in {
      val outputRead = CodeNarcOutput.loadXMLResult(resultFile)
      assert(outputRead.text == codeNarcXmlResult.text)
    }

    "not contain 0 elements" in {
      val xmlResult = CodeNarcOutput.parseXmlResult(codeNarcXmlResult)
      assert(xmlResult.nonEmpty)
    }

    "return as the first element the violation NoTabCharacter on GroovyDeveloper.groovy file (line 0)" in {
      val xmlResult = CodeNarcOutput.parseXmlResult(codeNarcXmlResult)
      val firstFileViolation = xmlResult.toList.head
      assert(firstFileViolation.file == "src/main/groovy/com/nick/GroovyDeveloper.groovy")
      assert(firstFileViolation.ruleName == "NoTabCharacter")
      assert(firstFileViolation.message == "The tab character is not allowed in source files")
      assert(firstFileViolation.line == 0)
    }

    "return as the second element the violation UnnecessarySemicolon on GroovyDeveloper.groovy file (line 2)" in {
      val xmlResult = CodeNarcOutput.parseXmlResult(codeNarcXmlResult)
      val firstFileViolation = xmlResult.toList(1)
      assert(firstFileViolation.file == "src/main/groovy/com/nick/GroovyDeveloper.groovy")
      assert(firstFileViolation.ruleName == "UnnecessarySemicolon")
      assert(firstFileViolation.message == "Semi-colons as line endings can be removed safely")
      assert(firstFileViolation.line == 2)
    }
  }
}

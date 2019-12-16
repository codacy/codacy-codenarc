package codacy.codenarc

import java.net.URL

import better.files.File
import org.scalatest.BeforeAndAfterAll
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.xml.{Elem, XML}

class CodeNarcOutputSpec extends AnyWordSpec with Matchers with BeforeAndAfterAll {
  val codeNarcXmlResultResource: URL = getClass.getResource("/codeNarcXmlResult.xml")
  val codeNarcXmlResult: Elem = XML.load(codeNarcXmlResultResource)

  val resultFile: File = File.newTemporaryFile("result", ".xml").write(codeNarcXmlResult.toString)

  val resultAsSequence: Seq[CodeNarcOutput.CodeNarcOutput] = Seq(
    CodeNarcOutput.CodeNarcOutput(
      "src/main/groovy/com/nick/GroovyDeveloper.groovy",
      "The tab character is not allowed in source files",
      "NoTabCharacter",
      0
    ),
    CodeNarcOutput.CodeNarcOutput(
      "src/main/groovy/com/nick/GroovyDeveloper.groovy",
      "Semi-colons as line endings can be removed safely",
      "UnnecessarySemicolon",
      2
    ),
    CodeNarcOutput.CodeNarcOutput(
      "src/main/groovy/com/nick/GroovyDeveloper.groovy",
      "File GroovyDeveloper.groovy has consecutive blank lines",
      "ConsecutiveBlankLines",
      3
    ),
    CodeNarcOutput.CodeNarcOutput(
      "src/main/groovy/com/nick/GroovyDeveloper.groovy",
      "Semi-colons as line endings can be removed safely",
      "UnnecessarySemicolon",
      7
    ),
    CodeNarcOutput.CodeNarcOutput(
      "src/main/groovy/com/nick/GroovyDeveloper.groovy",
      "Semi-colons as line endings can be removed safely",
      "UnnecessarySemicolon",
      10
    ),
    CodeNarcOutput.CodeNarcOutput(
      "src/main/groovy/com/nick/GroovyDeveloper.groovy",
      "Semi-colons as line endings can be removed safely",
      "UnnecessarySemicolon",
      11
    )
  )

  override def afterAll() {
    resultFile.delete()
  }

  "parsing the XML result file" should {
    "not contain 0 elements" in {
      val xmlResult = CodeNarcOutput.parseXmlResult(codeNarcXmlResult)
      assert(xmlResult.nonEmpty)
    }

    "return as the first element the violation NoTabCharacter on GroovyDeveloper.groovy file (line 0)" in {
      val xmlResult = CodeNarcOutput.parseXmlResult(codeNarcXmlResult)

      xmlResult should be(resultAsSequence)
    }
  }
}

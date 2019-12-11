package codacy.codenarc

import better.files.File
import org.scalatest._

class CodeNarcOutputSpec extends WordSpec {
  val codeNarcXmlResult = <CodeNarc version="1.5" url="http://www.codenarc.org">
    <Report timestamp="Dec 11, 2019 10:06:54 AM"/>
    <Project title="">
      <SourceDirectory>/src</SourceDirectory>
    </Project>
    <PackageSummary priority3="22" priority2="3" priority1="0" filesWithViolations="2" totalFiles="2"/>
    <Package priority3="22" priority2="3" priority1="0" filesWithViolations="2" totalFiles="2" path=""/>
    <Package priority3="22" priority2="3" priority1="0" filesWithViolations="2" totalFiles="2" path="src"/>
    <Package priority3="16" priority2="3" priority1="0" filesWithViolations="1" totalFiles="1" path="src/test"/>
    <Package priority3="16" priority2="3" priority1="0" filesWithViolations="1" totalFiles="1" path="src/test/groovy"/>
    <Package priority3="16" priority2="3" priority1="0" filesWithViolations="1" totalFiles="1" path="src/test/groovy/com"/>
    <Package priority3="6" priority2="0" priority1="0" filesWithViolations="1" totalFiles="1" path="src/main"/>
    <Package priority3="6" priority2="0" priority1="0" filesWithViolations="1" totalFiles="1" path="src/main/groovy"/>
    <Package priority3="6" priority2="0" priority1="0" filesWithViolations="1" totalFiles="1" path="src/main/groovy/com"/>
    <Package priority3="6" priority2="0" priority1="0" filesWithViolations="1" totalFiles="1" path="src/main/groovy/com/nick">
      <File name="GroovyDeveloper.groovy">
        <Violation lineNumber="" priority="3" ruleName="NoTabCharacter">
          <Message>The tab character is not allowed in source files</Message>
        </Violation>
        <Violation lineNumber="2" priority="3" ruleName="UnnecessarySemicolon">
          <SourceLine>import com.nick.JavaDeveloper;</SourceLine>
          <Message>Semi-colons as line endings can be removed safely</Message>
        </Violation>
        <Violation lineNumber="3" priority="3" ruleName="ConsecutiveBlankLines">
          <Message>File GroovyDeveloper.groovy has consecutive blank lines</Message>
        </Violation>
        <Violation lineNumber="7" priority="3" ruleName="UnnecessarySemicolon">
          <SourceLine>private PrintStream output;</SourceLine>
          <Message>Semi-colons as line endings can be removed safely</Message>
        </Violation>
        <Violation lineNumber="10" priority="3" ruleName="UnnecessarySemicolon">
          <SourceLine>super(output);</SourceLine>
          <Message>Semi-colons as line endings can be removed safely</Message>
        </Violation>
        <Violation lineNumber="11" priority="3" ruleName="UnnecessarySemicolon">
          <SourceLine>this.output = output;</SourceLine>
          <Message>Semi-colons as line endings can be removed safely</Message>
        </Violation>
      </File>
    </Package>
  </CodeNarc>

  val resultFile = File.newTemporaryFile("result", ".xml").write(codeNarcXmlResult.toString)

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
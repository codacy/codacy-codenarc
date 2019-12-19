package codacy.codenarc

import codacy.codenarc.CodeNarc.PatternParameter
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import play.api.libs.json.{JsNumber, JsString}

class CodeNarcSpec extends AnyWordSpec with Matchers {
  "ruleFileFromPatterns" should {
    "return a valid CodeNarc rule file" in {
      val patterns: List[CodeNarc.Pattern] =
        List(
          CodeNarc.Pattern(
            "AssignmentInConditional",
            Set(PatternParameter("Example", JsNumber(2)), PatternParameter("Param", JsString("exampleVal")))
          ),
          CodeNarc.Pattern("BigDecimalInstantiation", Set())
        )

      val expectedValue =
        s"""ruleset {
           |AssignmentInConditional {
           |Example = 2
           |Param = 'exampleVal'
           |}
           |BigDecimalInstantiation
           |}""".stripMargin

      CodeNarc.ruleFileContentFromPatterns(patterns) should be(expectedValue)
    }
  }
}

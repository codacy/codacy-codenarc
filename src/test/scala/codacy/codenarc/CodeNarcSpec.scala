package codacy.codenarc

import codacy.codenarc.CodeNarc.PatternParameter
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class CodeNarcSpec extends AnyWordSpec with Matchers {
  "ruleFileFromPatterns" should {
    "return a valid CodeNarc rule file" in {
      val patterns: List[CodeNarc.Pattern] =
        List(
          CodeNarc.Pattern(
            "AssignmentInConditional",
            Set(PatternParameter("Example", 2), PatternParameter("Param", "exampleVal"))
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

package codacy.codenarc

import org.specs2.mutable.Specification

class CodeNarcSpec extends Specification {
  "ruleFileFromPatterns" should {
    "return a valid CodeNarc rule file" in {
      val patterns: List[CodeNarc.PatternTuple] = List(
        CodeNarc.PatternTuple("AssignmentInConditional", ""),
        CodeNarc.PatternTuple("BigDecimalInstantiation", ""),
      )

      val expectedValue =
        s"""ruleset {
           |AssignmentInConditional
           |BigDecimalInstantiation
           |}""".stripMargin

      CodeNarc.ruleFileContentFromPatterns(patterns) must beEqualTo(expectedValue)
    }
  }
}

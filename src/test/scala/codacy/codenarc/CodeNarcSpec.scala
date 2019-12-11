package codacy.codenarc

import org.scalatest._

class CodeNarcSpec extends WordSpec {
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

      assert(CodeNarc.ruleFileContentFromPatterns(patterns) == expectedValue)
    }
  }
}

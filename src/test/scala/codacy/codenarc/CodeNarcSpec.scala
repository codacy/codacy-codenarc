package codacy.codenarc

import codacy.codenarc.CodeNarc.PatternParameter
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import play.api.libs.json.{JsNumber, JsString}

class CodeNarcSpec extends AnyWordSpec with Matchers {
  "ruleFileFromPatterns" should {
    "return a valid CodeNarc rule file parameter values having regular value" in {
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

    "return a valid CodeNarc rule file with parameter values having regex values" in {
      val regex1 = ".*(Spec|Test|Tests|TestCase)\\.groovy" // before parsing -> [.*(Spec|Test|Tests|TestCase)\.groovy]
      val regex2 = "I\\u2019m" // before parsing -> ["I\u2019m"]
      val patterns: List[CodeNarc.Pattern] = {
        List(
          CodeNarc.Pattern(
            "AssignmentInConditional",
            Set(PatternParameter("Example", JsNumber(2)), PatternParameter("Param", JsString(regex1)))
          ),
          CodeNarc.Pattern(
            "BigDecimalInstantiation",
            Set(PatternParameter("Example", JsNumber(2)), PatternParameter("Param", JsString(regex2)))
          )
        )
      }

      val expectedValue =
        s"""ruleset {
           |AssignmentInConditional {
           |Example = 2
           |Param = '.*(Spec|Test|Tests|TestCase)\\\\.groovy'
           |}
           |BigDecimalInstantiation {
           |Example = 2
           |Param = 'I\\\\u2019m'
           |}
           |}""".stripMargin

      /*
       --expectedValue output--
       [ruleset {
          AssignmentInConditional {
            Example = 2
            Param = '.*(Spec|Test|Tests|TestCase)\\.groovy'
          }
          BigDecimalInstantiation {
            Example = 2
            Param = 'I\\u2019m'
          }
        }]
       */
      CodeNarc.ruleFileContentFromPatterns(patterns) should be(expectedValue)
    }
  }
}

package codacy.codenarc

import org.codenarc.results.{DirectoryResults, FileResults, Results}
import org.codenarc.rule.Violation

object CodeNarcOutput {
  case class CodeNarcOutput(file: String, message: String, ruleName: String, line: Int)

  case class CodeNarcViolation(ruleName: String, message: String, line: Int)

  private def parseFileResult(fileResults: FileResults): List[CodeNarcOutput] = {
    fileResults.getViolations
      .toArray()
      .map {
        case violation: Violation =>
          val ruleTitle = violation.getRule.getName
          CodeNarcOutput(
            fileResults.getPath,
            Option(violation.getMessage).getOrElse(ruleTitle),
            ruleTitle,
            violation.getLineNumber.toInt
          )
      }
      .toList
  }

  def parseResult(result: Results): List[CodeNarcOutput] =
    result match {
      case fResult: FileResults =>
        parseFileResult(fResult)

      case dirResult: DirectoryResults =>
        dirResult.getChildren.toArray.flatMap {
          case res: Results => parseResult(res)
        }.toList

      case _ => List()
    }
}

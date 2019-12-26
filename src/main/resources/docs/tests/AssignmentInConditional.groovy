//#Patterns: AssignmentInConditional
package docs.tests

class AssignmentInConditional {
    public ConsecutiveBlankLines() {
        //#Warning: AssignmentInConditional
        if ((output = null)) {
            output.println("ERROR")
        }
    }
}

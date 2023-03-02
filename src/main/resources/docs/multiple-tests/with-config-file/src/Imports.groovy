//#Patterns: MissingBlankLineAfterPackage, MisorderedStaticImports, UnnecessarySemicolon
//#Info: MissingBlankLineAfterPackage
package docs.tests
//#Info: MisorderedStaticImports
import static ConsecutiveBlankLines.*

class Imports {
    private ConsecutiveBlankLines consecutiveBlankLines

    PrintStream output

    void beforeTest() {
        //#Info: UnnecessarySemicolon
        consecutiveBlankLines = new ConsecutiveBlankLines(output);
        println(Testing())
        //#Info: UnnecessarySemicolon
        println(Math.sqrt(1));
    }
}

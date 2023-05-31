package docs.tests

import static ConsecutiveBlankLines.*

class Imports {

    private ConsecutiveBlankLines consecutiveBlankLines

    PrintStream output

    void beforeTest() {
        consecutiveBlankLines = new ConsecutiveBlankLines(output);
        println(Testing())
        println(Math.sqrt(1));
    }
}

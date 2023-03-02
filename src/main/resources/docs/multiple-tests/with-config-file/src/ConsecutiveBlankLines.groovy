package docs.tests


class ConsecutiveBlankLines {
    static int Testing() {
        return 2
    }

    private PrintStream output;

    public ConsecutiveBlankLines(PrintStream output) {
     super(output);
        this.output = output;
        if (this.output = null) {
            output.println("ERROR")
        }
    }
}

Checks for empty *finally* blocks. Empty *finally* blocks are confusing
and serve no purpose.

Here is an example of code that produces a violation:

        def myMethod() {
            try {
                doSomething()
            } finally {
                // empty
            }
        }

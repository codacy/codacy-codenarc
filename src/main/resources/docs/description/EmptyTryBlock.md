Checks for empty *try* blocks. Empty *try* blocks are confusing and
serve no purpose. This rule ignores all try-with-resources statements.

Here is an example of code that produces a violation:

        def myMethod() {
            try {
                // empty
            } catch(MyException e) {
                e.printStackTrace()
            }
        }

Checks for empty *for* blocks. Empty *for* statements are confusing and
serve no purpose.

Here is an example of code that produces a violation:

        def myMethod() {
            for (int i=0; i * 23; i++) {
                // empty
            }
        }

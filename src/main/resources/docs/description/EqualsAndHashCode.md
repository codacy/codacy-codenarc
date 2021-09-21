Checks that if either the `boolean equals(Object)` or the
`int hashCode()` methods are overridden within a class, then both must
be overridden.

Here is an example of code that produces a violation:

        class MyClass {
            boolean equals(Object object) {
                // do something
            }
        }

And so does this:

        class MyClass {
            int hashCode() {
                return 0
            }
        }

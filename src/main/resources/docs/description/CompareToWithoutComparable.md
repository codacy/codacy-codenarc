*Since CodeNarc 0.12*

If you implement a compareTo method then you should also implement the
`Comparable` interface. If you don’t then you could possibly get an
exception if the Groovy == operator is invoked on your object. This is
an issue fixed in Groovy 1.8 but present in previous versions.

This rule has a single `enhancedMode` property which defaults to
`false`. When set to `true`, this rule will run in [enhanced
mode](./codenarc-enhanced-classpath-rules.html) and will not produce a
violation when a class implements `compareTo` and extends a class that
itself implements `Comparable`.

Here is an example of code that produces a violation:

        class BadClass {
            int compareTo(Object o) { ... }
        }

Known limitations:

- When not running in enhanced mode, this rule is not able to determine
  if the class extends a superclass that itself implements `Comparable`,
  or if it implements an interface that extends `Comparable`. In those
  cases, this rule produces a false violation.

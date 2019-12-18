
*Since CodeNarc 0.12*

If a class defines a `void close()` method then that class should implement `java.io.Closeable`.

This rule has a single `enhancedMode` property which defaults to `false`. When set to `true`, this rule
will run in [enhanced mode](./codenarc-enhanced-classpath-rules.html) and will not produce a violation when a class
implements `close` and extends a class that itself implements `Closeable`.


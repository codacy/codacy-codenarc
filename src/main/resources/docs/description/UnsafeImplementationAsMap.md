*Since CodeNarc 0.19*

Reports incomplete interface implementations created by map-to-interface
coercions.

By default, this rule does not apply to test files.

NOTE: This is a [CodeNarc Enhanced Classpath
Rule](./codenarc-enhanced-classpath-rules.html). It requires
**CodeNarc** to have the application classes being analyzed, as well as
any referenced classes, on the classpath.

Example of violations:

``` 
    [mouseClicked: { ... }] as MouseListener
    //not all MouseListener methods are implemented which can lead to UnsupportedOperationException-s
```

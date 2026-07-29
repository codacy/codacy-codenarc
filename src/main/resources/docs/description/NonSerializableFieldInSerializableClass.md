*Since CodeNarc 3.5.0*

All fields of a `Serializable` class should also implement
`Serializable`.

NOTE: This is a [CodeNarc Enhanced Classpath
Rule](./codenarc-enhanced-classpath-rules.html). It requires
**CodeNarc** to have the application classes being analyzed, as well as
any referenced classes, on the classpath.

Examples:

    class SerializableClass implements Serializable {
        private SerializableClass field     // OK
        private String field                // OK
        private OtherClass otherField       // Violation
    }

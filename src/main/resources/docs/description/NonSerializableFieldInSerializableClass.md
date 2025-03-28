*Since CodeNarc 3.5.0*

All fields of a `Serializable` class should also implement
`Serializable`.

Examples:

    class SerializableClass implements Serializable {
        private SerializableClass field     // OK
        private String field                // OK
        private OtherClass otherField       // Violation
    }

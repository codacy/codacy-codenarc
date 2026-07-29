*Since CodeNarc 3.5.0*

Every parameter and return type has to implement the Serializable
interface in Jenkins CPS tranformed Code.

NOTE: This is a [CodeNarc Enhanced Classpath
Rule](./codenarc-enhanced-classpath-rules.html). It requires
**CodeNarc** to have the application classes being analyzed, as well as
any referenced classes, on the classpath.

Examples:

    class SomeClass {}
    class SerializableClass implements Serializable {}

    void method(int i, SomeClass s) {}                                // Violation
    SomeClass returnMethod() { return null }                          // Violation
    SerializableClass safeMethod(SerializableClass s) { return null } // OK

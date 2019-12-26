
*Since CodeNarc 1.1*

Checks that field types are explicitly specified (and not using `def`).

| Property                    | Description            | Default Value    |
|-----------------------------|------------------------|------------------|
| ignoreFieldNames            | Specifies one or more (comma-separated) field names that should be ignored (i.e., that should not cause a rule violation). The names may optionally contain wildcards (*,?).  | `null`  |

Example of violations:

```
    class MyClass {
        public static final NAME = "joe"        // violation
        private static count = 0                // violation

        private def name = NAME                 // violation
        protected final date = new Date()       // violation

        def defaultName                         // violation
        def maxSoFar = -1L                      // violation
    }
```

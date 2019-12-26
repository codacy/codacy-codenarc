
*Since CodeNarc 0.24*

Checks for assignment to a static field from an instance method.

Influenced by the **AssignmentToNonFinalStatic** rule from **PMD**, and the
**ST_WRITE_TO_STATIC_FROM_INSTANCE_METHOD** rule from **FindBugs**.

Example of violations:

```
    class MyClass {
        private static field1
        protected static String field2 = 'abc'
        public static int field3 = 123
        static String property1 = 'abc'
        private static final NAME = 'joe'

        private void doStuff() {
            field1 = new Object()       // violation
            field2 = 'xxx'              // violation
            field3 = 999                // violation
            property1 = 'xxx'           // violation

            final NAME = 'martin'       // no violation; local var hides static field
        }
    }
```


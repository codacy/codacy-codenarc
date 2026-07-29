*Since CodeNarc 2.0.0*

Do not use an `Optional` as a field type.

Example of violations:

        class MyClass {
            Optional<Integer> count;                            // violation
            public String name;
            public Optional<String> alias = Optional.of("x")    // violation
            protected static Optional<Object> lock              // violation
        }

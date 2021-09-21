*Since CodeNarc 1.2*

Enforce that all public methods are above protected and private methods.

Example of violations:

        class MyClass {
            public static int staticMethod1() { }

            protected String method1() { }

            static final String staticMethod2() { }     // violation
            public String method2() { }                 // violation

            private int method3(int id) { }
        }

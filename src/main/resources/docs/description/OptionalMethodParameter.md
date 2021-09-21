*Since CodeNarc 2.0.0*

Do not use an `Optional` as a parameter type for a method or
constructor. See [The Java Optional class: 11 more recipes for
preventing null pointer
exceptions](https://blogs.oracle.com/javamagazine/the-java-optional-class-11-more-recipes-for-preventing-null-pointer-exceptions).

Example of violations:

        class MyClass {
            protected MyClass(Optional<Integer> count) { }                  // violation
            MyClass(Optional<String> name, Optional<Integer> sum) { }       // 2 violations
            private MyClass(Optional something) { }                         // violation

            void doStuff(Optional<Integer> count) { }                       // violation
            public String getName() { return 'abc' }
            int count(Optional<String> alias, Optional<Integer> total) { }  // 2 violations
            private doSomething(Optional something) { }                     // violation
        }

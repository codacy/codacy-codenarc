*Since CodeNarc 1.2*

Enforce that all static fields are above all instance fields within a
class

Example of violations:

``` 
    class MyClass {
        public static final int COUNT = 99

        public String f1

        public static final String F1 = "xxx"       // violation
        private static String F4                    // violation
        static F5 = new Date()                      // violation

        protected String f2
    }
```

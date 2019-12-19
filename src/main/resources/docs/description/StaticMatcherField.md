
*Since CodeNarc 0.13*

Matcher objects should not be used as static fields. Calendars are inherently unsafe for multithreaded use. Sharing a single
instance across thread boundaries without proper synchronization will result in erratic behavior of the application.

Example of violations:

```
    // two violations
    class MyClass {
      static Matcher matcher1
      static java.util.regex.Matcher matcher2
    }

    // these usages are OK
    class MyCorrectClass {
      private Matcher matcher1
      static ThreadLocal*Matcher* matcher2
    }
```



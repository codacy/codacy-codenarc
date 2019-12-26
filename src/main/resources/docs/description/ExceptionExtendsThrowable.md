
*Since CodeNarc 0.21*

Checks for classes that extend `Throwable`. Custom exception classes should subclass `Exception`
or one of its descendants.

Example of violations:

```
    class MyException extends Throwable { }   // violation
```


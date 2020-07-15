*Since CodeNarc 0.13*

Errors are system exceptions. Do not extend them.

Examples:

``` 
    class MyError extends Error { }  // violation
    class MyError extends java.lang.Error { }  // violation

    class MyException extends Exception { }  // OK
```

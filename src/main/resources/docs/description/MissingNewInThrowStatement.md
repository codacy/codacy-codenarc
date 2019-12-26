
*Since CodeNarc 0.12*

A common Groovy mistake when throwing exceptions is to forget the new keyword. For instance, `throw RuntimeException()`
instead of `throw new RuntimeException()`. If the error path is not unit tested then the production system will
throw a Method Missing exception and hide the root cause. This rule finds constructs like `throw RuntimeException()` that
look like a new keyword was meant to be used but forgotten.

The following code will all cause violations:

```
    throw RuntimeException()    // ends in Exceptions, first letter Capitalized
    throw RuntimeFailure()      // ends in Failure, first letter Capitalized
    throw RuntimeFault(foo)     // ends in Fault, first letter Capitalized
```

The following code will not cause any exceptions:

```
    throw new RuntimeException()
    throw runtimeFailure()      // first letter lowercase, assumed to be method call
```


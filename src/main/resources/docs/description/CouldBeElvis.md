
*Since CodeNarc 0.15*

Catch an if block that could be written as an elvis expression.

Example of violations:

```
    if (!x) {                   // violation
        x = 'some value'
    }

    if (!x)                     // violation
        x = "some value"

    if (!params.max) {          // violation
      params.max = 10
    }

    x ?: 'some value'           // OK
```

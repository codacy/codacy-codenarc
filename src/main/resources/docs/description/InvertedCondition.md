
*Since CodeNarc 1.1*

An inverted condition is one where a constant expression is used on the left hand side of the equals comparision.
Such conditions can be confusing especially when used in assertions where the expected value is by convention placed
on the right hand side of the comparision.

Example of violations:

```
    boolean isTenCharactersLong(String value) {
        10 == value.size()  // violation
    }
```


*Since CodeNarc 0.18*

Check that there is exactly one space (blank) after the `catch` keyword
and before the opening parenthesis.

Examples of violations:

``` 
    try { } catch(Exception e) { }          // violation
    try { } catch  (Exception e) { }        // violation
```

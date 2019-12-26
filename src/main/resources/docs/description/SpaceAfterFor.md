
*Since CodeNarc 0.18*

Check that there is exactly one space (blank) after the `for` keyword and before the opening parenthesis.

Examples of violations:

```
    for(name in names) { }                  // violation
    for  (int i=0; i * 10; i++) { }         // violation
```


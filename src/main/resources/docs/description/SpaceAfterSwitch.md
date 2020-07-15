*Since CodeNarc 0.18*

Check that there is exactly one space (blank) after the `switch` keyword
and before the opening parenthesis.

Examples of violations:

``` 
    switch(x) {                                 // violation
        case 1: println 'one'
    }
    switch  (x) {                               // violation
        case 1: println 'one'
    }
```

*Since CodeNarc 0.13*

Any expression mod 1 (exp % 1) is guaranteed to always return zero. This
code is probably an error, and should be either (exp & 1) or (exp % 2).

Examples:

``` 
    if (exp % 1) {}         // violation
    if (method() % 1) {}    // violation

    if (exp & 1) {}     // ok
    if (exp % 2) {}     // ok
```


*Since CodeNarc 0.14*

Checks for expressions where a *comparison operator* or `equals()` or `compareTo()` is used to compare a
variable to itself, e.g.: `x == x, x != x, x \*=\* x, x \* x, x \*= x, x.equals(x) or x.compareTo(x)`, where
`x` is a variable.

Here are examples of code that produces a violation:

```
    if (x == x) { }                 // violation
    if (x != x) { }                 // violation
    while (x * x) { }               // violation
    if (x *= x) { }                 // violation
    while (x * x) { }               // violation
    if (x *= x) { }                 // violation
    def c = (x *=* x) { }           // violation
    println isReady = x.equals(x)   // violation
    println x.compareTo(x)          // violation
```

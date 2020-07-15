*Since CodeNarc 0.12 (formerly BooleanInstantiation Rule in the “basic”
rule set)*

Checks for direct call to a `Boolean` constructor. Use
`Boolean.valueOf()` or the `Boolean.TRUE` and `Boolean.FALSE` constants
instead of calling the `Boolean()` constructor directly.

Also checks for `Boolean.valueOf(true)` or `Boolean.valueOf(false)`. Use
the `Boolean.TRUE` or `Boolean.FALSE` constants instead.

Here is an example of code that produces a violation:

``` 
    def b1 = new Boolean(true)             // violation
    def b2 = new java.lang.Boolean(false)  // violation
    def b3 = Boolean.valueOf(true)         // violation
    def b4 = Boolean.valueOf(false)        // violation
```

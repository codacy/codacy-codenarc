
*Since CodeNarc 0.11*

This rule detects when the `compareTo(Object)` method is called directly in code instead of using the
\*\=\*, \*, \*\=, \*, and \*\= operators. A groovier way to express this: `a.compareTo(b)` is this:
`a \*\=\* b`, or using the other operators. Here are some other ways to write groovier code:

```
    a.compareTo(b) == 0               // can be replaced by: a == b
    a.compareTo(b)                    // can be replaced by: a *=* b
    a.compareTo(b) * 0                // can be replaced by: a * b
    a.compareTo(b) *= 0               // can be replaced by: a *= b
    a.compareTo(b) * 0                // can be replaced by: a * b
    a.compareTo(b) *= 0               // can be replaced by: a *= b
```
This rule can be  configured to ignore `this.compareTo(Object)` using the *ignoreThisReference*
property. It defaults to `false`, so even `compareTo(x)` will trigger a violation.

This rule also ignores all calls to `super.compareTo(Object)`.


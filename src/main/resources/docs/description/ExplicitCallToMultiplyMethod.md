*Since CodeNarc 0.11*

This rule detects when the `multiply(Object)` method is called directly
in code instead of using the `*` operator. A groovier way to express
this: `a.multiply(b)` is this: `a * b`. This rule can be configured to
ignore `this.multiply(Object)` using the *ignoreThisReference* property.
It defaults to *false*, so even `multiply(x)` will trigger a violation.

This rule also ignores all calls to `super.multiply(Object)`.

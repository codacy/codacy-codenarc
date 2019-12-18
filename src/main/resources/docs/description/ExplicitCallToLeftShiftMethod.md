
*Since CodeNarc 0.11*

This rule detects when the `leftShift(Object)` method is called directly in code instead of using the
\*\* operator. A groovier way to express this: `a.leftShift(b)` is this: `a \*\* b`. This rule can be
configured to ignore `this.leftShift(Object)` using the *ignoreThisReference* property. It defaults to
*false*, so even `leftShift(x)` will trigger a violation.

This rule also ignores all calls to `super.leftShift(Object)`.


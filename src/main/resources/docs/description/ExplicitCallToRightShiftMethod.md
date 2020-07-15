*Since CodeNarc 0.11*

This rule detects when the `rightShift(Object)` method is called
directly in code instead of using the \*\* operator. A groovier way to
express this: `a.rightShift(b)` is this: `a \*\* b`. This rule can be
configured to ignore `this.rightShift(Object)` using the
*ignoreThisReference* property. It defaults to *false*, so even
`rightShift(x)` will trigger a violation.

This rule also ignores all calls to `super.rightShift(Object)`.

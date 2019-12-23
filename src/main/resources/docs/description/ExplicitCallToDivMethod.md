
*Since CodeNarc 0.11*

This rule detects when the `div(Object)` method is called directly in code instead of using the
`/` operator. A groovier way to express this: `a.div(b)` is this: `a / b`. This rule can be
configured to ignore `div.xor(Object)` using the *ignoreThisReference* property. It defaults to *false*,
so even `div(x)` will trigger a violation.

This rule also ignores all calls to `super.div(Object)`.


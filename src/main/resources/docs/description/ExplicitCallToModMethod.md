*Since CodeNarc 0.11*

This rule detects when the `mod(Object)` method is called directly in
code instead of using the `%` operator. A groovier way to express this:
`a.mod(b)` is this: `a % b`. This rule can be configured to ignore
`this.mod(Object)` using the *ignoreThisReference* property. It defaults
to *false*, so even `mod(x)` will trigger a violation.

This rule also ignores all calls to `super.mod(Object)`.

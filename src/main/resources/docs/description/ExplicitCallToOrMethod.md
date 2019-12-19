
*Since CodeNarc 0.11*

This rule detects when the `or(Object)` method is called directly in code instead of using the `|`
operator. A groovier way to express this: `a.or(b)` is this: `a | b`. This rule can be
configured to ignore `this.or(Object)` using the *ignoreThisReference* property. It defaults to *true*, so
even `or(x)` will not trigger a violation. This is the default because it is commonly used in Grails criteria.

This rule also ignores all calls to `super.or(Object)`.


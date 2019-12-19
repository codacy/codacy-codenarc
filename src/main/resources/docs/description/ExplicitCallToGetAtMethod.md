
*Since CodeNarc 0.11*

This rule detects when the `getAt(Object)` method is called directly in code instead of using the
`[]` index operator. A groovier way to express this: `a.getAt(b)` is this: `a[b]`. This rule can be
configured to ignore `this.getAt(Object)` using the *ignoreThisReference* property. It defaults to *false*,
so even `getAt(x)` will trigger a violation.

This rule also ignores all calls to `super.getAt(Object)`.


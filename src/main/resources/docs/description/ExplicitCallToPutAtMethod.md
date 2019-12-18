
*Since CodeNarc 1.3*

Detects when the `map.putAt(k, v)` method is called directly rather than using `map[k] = v`.

This rule can be configured to ignore `this.putAt(k, v)` using the *ignoreThisReference* property. It defaults
to *false*, so even `putAt(k, v)` will trigger a violation.

This rule also ignores all calls to `super.putAt(k, v)`.

Example of violations:

```
        map.putAt(k, v)         // violation
```



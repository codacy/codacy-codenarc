
*Since CodeNarc 0.15*

This rule finds instanceof checks that cannot possibly evaluate to true. For instance, checking that
`(!variable instanceof String)` will never be true because the result of a not expression is always a boolean.

Example of violations:

```
    if (!variable instanceof String) { ... }    // always false
    def x = !variable instanceof String         // always false

    if (!variable instanceof Boolean) { ... }    // always true
    def x = !variable instanceof Boolean         // always true

    // this code is OK
    if (!(variable instanceof String)) { ... }
```


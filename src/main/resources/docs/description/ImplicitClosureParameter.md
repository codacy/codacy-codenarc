
*Since CodeNarc 1.5*

Checks for the implicit `it` closure parameter being used.
Also checks if an explicit `it` parameter has been specified.

| Property                    | Description            | Default Value    |
|-----------------------------|------------------------|------------------|
| allowUsingItAsParameterName | To stop the rule reporting violations when an explicit closure parameter called `it` is used, set this property to `true`. | `false` |

Example of violations:

```
    def closureWithViolation = { it * 10 }
    def closureWithViolationBecauseOfExplicitItParameter = { it -* it * 10}
```



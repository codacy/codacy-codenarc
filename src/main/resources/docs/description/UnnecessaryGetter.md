
*Since CodeNarc 0.12*

Checks for explicit calls to getter/accessor methods which can, for the most part, be replaced by property access.
A getter is defined as a no-argument method call that matches `get[A-Z]` but not `getClass()` or
`get[A-Z][A-Z]` such as `getURL()`.

Calls to getter methods within Spock method calls `Mock()`, `Stub()` and `Spy()` are ignored.

| Property                    | Description            | Default Value    |
|-----------------------------|------------------------|------------------|
| checkIsMethods              | If `true`, then also check isXxx() getters methods. | `true` |
| ignoreMethodNames           | Specifies one or more (comma-separated) method names that should be ignored (i.e., that should not cause a rule violation). The names may optionally contain wildcards (*,?).  | `null` |

These bits of code produce violations:

```
    x.getProperty()
    x.getFirst()
    x.getFirstName()
    x.getA()

    x.isFirst()         // Violation if checkIsMethods is true
    x.isA()             // Violation if checkIsMethods is true
```

These bits of code do not:

```
    x.property
    x.first
    x.firstName
    x.a
    x.getURL()
    x.getClass()
    x.getProperty('key')
```


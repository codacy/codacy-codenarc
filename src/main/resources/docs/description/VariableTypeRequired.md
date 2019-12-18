
*Since CodeNarc 1.1*

Checks that variable types are explicitly specified in declarations (and not using `def`).

| Property                    | Description            | Default Value    |
|-----------------------------|------------------------|------------------|
| ignoreVariableNames         | Specifies one or more (comma-separated) variable names that should be ignored (i.e., that should not cause a rule violation). The names may optionally contain wildcards (*,?).  | `null`|

Example of violations:

```
    class MyClass {
        void doStuff() {
            final NAME = "joe"          // violation
            def count = 0, max = 99     // violation
            def defaultName             // violation
        }
    }
```


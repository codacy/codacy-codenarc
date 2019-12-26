
*Since CodeNarc 0.21*

Checks for wildcard (star) imports. If the *ignoreStaticImports* property is *true*, then do not check static imports.
Similarly, do not check the standard imports if ignoreImports is *true*.

Example of violations:

```
    import static foo.bar.*         // violation (unless ignoreStaticImports is true)
    import my.something.*           // violation (unless ignoreImports is true)

    public class MyClass{}
```

| Property                    | Description            | Default Value    |
|-----------------------------|------------------------|------------------|
| ignoreStaticImports         | If *true*, then do not check static imports. | `false` |
| ignoreImports               | If *true*, then do not check imports.        | `false` |

NOTE: This is a file-based rule, rather than a typical AST-based rule, so the *applyToClassNames*
and *doNotApplyToClassNames* rule configuration properties are not available. See
[Standard Properties for Configuring Rules](./codenarc-configuring-rules.html#standard-properties-for-configuring-rules).

*Since CodeNarc 0.14* Avoid importing anything from the ’sun.\*’
packages. These packages are not portable and are likely to change.

Example of violations:

``` 
    import sun.misc.foo
    import sun.misc.foo as Foo

    public class MyClass{}
```

NOTE: This is a file-based rule, rather than a typical AST-based rule,
so the *applyToClassNames* and *doNotApplyToClassNames* rule
configuration properties are not available. See \[Standard Properties
for Configuring Rules\](./codenarc-configuring-rules.htm

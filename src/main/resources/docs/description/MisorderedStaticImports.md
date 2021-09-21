*Since CodeNarc 0.14* Checks for static *import* statements which should
never be after nonstatic imports.

This rule has one property `comesBefore`, which defaults to true. If you
like your static imports to come after the others, then set this
property to false.

Examples of violations:

        import my.something.another
        import static foo.bar

        public class MyClass{}

NOTE: This is a file-based rule, rather than a typical AST-based rule,
so the *applyToClassNames* and *doNotApplyToClassNames* rule
configuration properties are not available. See [Standard Properties for
Configuring
Rules](./codenarc-configuring-rules.html#standard-properties-for-configuring-rules).

*Since CodeNarc 0.15*

Checks the location of the opening brace ({) for classes. By default,
requires them on the same line, but the `sameLine` property can be set
to false to override this.

NOTE: This rule ignores annotation types, e.g. `@interface MyAnnotation
{}`.

NOTE: This is a file-based rule, rather than an AST-based rule, so the
*applyToClassNames* and *doNotApplyToClassNames* rule configuration
properties are not available. See [Standard Properties for Configuring
Rules](./codenarc-configuring-rules.html#standard-properties-for-configuring-rules).

*Since CodeNarc 0.25*

Checks that all source files do not contain the tab character.

NOTE: This is a file-based rule, rather than an AST-based rule, so the
*applyToClassNames* and *doNotApplyToClassNames* rule configuration
properties are not available. See [Standard Properties for Configuring
Rules](./codenarc-configuring-rules.html#standard-properties-for-configuring-rules).
The `@SuppressWarnings` annotation-based disablement is also
unavailable, but including a `// codenarc-disable NoTabCharacter`
comment somewhere above the violation will disable this rule. See
\[Disabling Rules From Comments\](./codenarc-configuring-rules.htm

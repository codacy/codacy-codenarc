
*Since CodeNarc 1.3*

Checks for empty @throws tag within javadoc.

Known limitation: Only the first occurrence of an empty @throws within a javadoc comment is found.

NOTE: This is a file-based rule, rather than an AST-based rule, so the *applyToClassNames* and
*doNotApplyToClassNames* rule configuration properties are not available. See
[Standard Properties for Configuring Rules](./codenarc-configuring-rules.htm
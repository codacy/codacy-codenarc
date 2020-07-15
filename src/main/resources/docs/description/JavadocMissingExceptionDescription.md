*Since CodeNarc 1.4*

Checks for missing description within @exception javadoc tags.

Known limitation: Only the first occurrence of a missing description for
an @exception javadoc comment is found.

NOTE: This is a file-based rule, rather than an AST-based rule, so the
*applyToClassNames* and *doNotApplyToClassNames* rule configuration
properties are not available. See \[Standard Properties for Configuring
Rules\](./codenarc-configuring-rules.htm

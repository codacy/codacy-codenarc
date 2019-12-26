
*Since CodeNarc 0.20*

Checks for a specified illegal string within the source code.

| Property                    | Description            | Default Value    |
|-----------------------------|------------------------|------------------|
| string                      | The String to check for. If null or empty then do nothing.     | `null` |

A RuleSet can contain any number of instances of this rule, but each should be configured
with a unique rule *name* and *string*, and (optionally) customized *violationMessage* and *priority*.

NOTE: This is a file-based rule, rather than an AST-based rule, so the *applyToClassNames* and
*doNotApplyToClassNames* rule configuration properties are not available. See
[Standard Properties for Configuring Rules](./codenarc-configuring-rules.html#standard-properties-for-configuring-rules).

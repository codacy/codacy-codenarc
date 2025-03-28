*Since CodeNarc 0.20*

Checks for a specified illegal string within the source code.

<table>
<colgroup>
<col style="width: 40%" />
<col style="width: 33%" />
<col style="width: 25%" />
</colgroup>
<thead>
<tr>
<th>Property</th>
<th>Description</th>
<th>Default Value</th>
</tr>
</thead>
<tbody>
<tr>
<td>string</td>
<td>The String to check for. If null or empty then do nothing.</td>
<td><code>null</code></td>
</tr>
</tbody>
</table>

A RuleSet can contain any number of instances of this rule, but each
should be configured with a unique rule *name* and *string*, and
(optionally) customized *violationMessage* and *priority*.

NOTE: This is a file-based rule, rather than an AST-based rule, so the
*applyToClassNames* and *doNotApplyToClassNames* rule configuration
properties are not available. See [Standard Properties for Configuring
Rules](./codenarc-configuring-rules.html#standard-properties-for-configuring-rules).
The `@SuppressWarnings` annotation-based disablement is also
unavailable, but including a `// codenarc-disable IllegalString` comment
somewhere above the violation will disable this rule. See [Disabling
Rules From
Comments](./codenarc-configuring-rules.html#disabling-rules-from-comments).

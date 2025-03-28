Checks for a specified text string that must exist within the source
code.

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

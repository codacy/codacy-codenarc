*Since CodeNarc 0.11*

This rule checks for duplicate String literals within the current class.

Code containing duplicate *String* literals can usually be improved by
declaring the *String* as a constant field.

This rule ignores (zero-length) empty strings.

By default, the rule does not analyze test files. This rule sets the
default value of the *doNotApplyToFilesMatching* property to ignore file
names ending in ‘Spec.groovy’, ‘Test.groovy’, ‘Tests.groovy’ or
‘TestCase.groovy’.

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
<td>ignoreStrings</td>
<td>The optional comma-separated list of Strings that should be ignored
(i.e., not cause a violation).</td>
<td><code>''</code> (empty string)</td>
</tr>
<tr>
<td>ignoreStringsDelimiter</td>
<td>The delimiter char for <code>ignoreStrings</code>.</td>
<td><code>,</code> (comma)</td>
</tr>
<tr>
<td>duplicateStringMinimumLength</td>
<td>Ignore duplicate strings whose length is less than this value</td>
<td><code>null</code></td>
</tr>
</tbody>
</table>

Verifies that the name of each variable matches a regular expression. By
default it checks that non-`final` variable names start with a lowercase
letter and contains only letters or numbers. By default, `final`
variable names start with an uppercase letter and contain only uppercase
letters, numbers and underscores.

<table>
<colgroup>
<col style="width: 40%" />
<col style="width: 33%" />
<col style="width: 25%" />
</colgroup>
<thead>
<tr class="header">
<th>Property</th>
<th>Description</th>
<th>Default Value</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td>regex</td>
<td>Specifies the default regular expression used to validate the variable name. It is required and cannot be null or empty.</td>
<td>[a-z][a-zA-Z0-9]*</td>
</tr>
<tr class="even">
<td>finalRegex</td>
<td>Specifies the regular expression used to validate <code>final</code> variable names. It is optional. If not set, then <strong>regex</strong> is used to validate <code>final</code> variable names.</td>
<td>[A-Z][A-Z0-9_]*</td>
</tr>
<tr class="odd">
<td>ignoreVariableNames</td>
<td>Specifies one or more (comma-separated) variable names that should be ignored (i.e., that should not cause a rule violation). The names may optionally contain wildcards (*,?).</td>
<td><code>null</code></td>
</tr>
</tbody>
</table>

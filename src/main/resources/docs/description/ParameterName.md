Verifies that the name of each parameter matches a regular expression.
This rule applies to method parameters, constructor parameters and
closure parameters. By default it checks that parameter names start with
a lowercase letter and contains only letters or numbers.

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
<td>Specifies the regular expression used to validate the parameter name. It is required and cannot be null or empty.</td>
<td>[a-z][a-zA-Z0-9]*</td>
</tr>
<tr class="even">
<td>ignoreParameterNames</td>
<td>Specifies one or more (comma-separated) parameter names that should be ignored (i.e., that should not cause a rule violation). The names may optionally contain wildcards (*,?).</td>
<td><code>null</code></td>
</tr>
</tbody>
</table>

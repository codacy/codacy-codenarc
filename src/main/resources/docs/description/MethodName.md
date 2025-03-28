Verifies that the name of each method matches a regular expression. By
default it checks that the method name starts with a lowercase letter.
Implicit method names are ignored (i.e., ‘main’ and ‘run’ methods
automatically created for Groovy scripts).

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
<td>regex</td>
<td>Specifies the regular expression used to validate the method name.
It is required and cannot be null or empty.</td>
<td>[a-z]\w*</td>
</tr>
<tr>
<td>ignoreMethodNames</td>
<td>Specifies one or more (comma-separated) method names that should be
ignored (i.e., that should not cause a rule violation). The names may
optionally contain wildcards (*,?).</td>
<td><code>null</code></td>
</tr>
</tbody>
</table>

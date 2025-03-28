Verifies that the name of a class matches a regular expression. By
default it checks that the class name starts with an uppercase letter
and is followed by zero or more word characters (letters, numbers or
underscores) or dollar signs ($).

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
<td>Specifies the regular expression used to validate the class name. It
is required and cannot be null or empty.</td>
<td>([A-Z]\w*\$?)*</td>
</tr>
</tbody>
</table>

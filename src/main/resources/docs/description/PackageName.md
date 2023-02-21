Verifies that the package name of a class matches a regular expression.
By default it checks that the package name consists of only lowercase
letters and numbers, separated by periods.

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
<td>Specifies the regular expression used to validate the package name.
It is required and cannot be null or empty.</td>
<td>[a-z]+[a-z0-9]<em>(\.[a-z0-9]+)</em></td>
</tr>
<tr class="even">
<td>packageNameRequired</td>
<td>Indicates whether a package name declaration is required for all
classes.</td>
<td><code>false</code></td>
</tr>
</tbody>
</table>

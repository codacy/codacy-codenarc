Checks if the size of a method exceeds the number of lines specified by
the **maxLines** property.

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
<td>maxLines</td>
<td>The maximum number of lines allowed in a method definition.</td>
<td>100</td>
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

Known Limitations:

- Annotations on a method are included in the size (line count) for that
  method.

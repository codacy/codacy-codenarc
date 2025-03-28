*Since CodeNarc 0.11*

Checks if the number of methods within a class exceeds the number of
lines specified by the **maxMethod** property.

A class with too many methods is probably a good suspect for
refactoring, in order to reduce its complexity and find a way to have
more fine grained objects.

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
<td>maxMethods</td>
<td>The maximum number of methods allowed in a class definition.</td>
<td>30</td>
</tr>
</tbody>
</table>

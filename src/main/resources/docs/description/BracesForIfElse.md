*Since CodeNarc 0.15*

Checks the location of the opening brace ({) for if statements. By
default, requires them on the same line, but the `sameLine` property can
be set to false to override this.

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
<td>sameLine</td>
<td>If <code>true</code>, then the opening brace ({) for if statement
should be on the same line.</td>
<td><code>true</code></td>
</tr>
<tr class="even">
<td>validateElse</td>
<td>To enable else checking, set the property to <code>true</code></td>
<td><code>false</code></td>
</tr>
<tr class="odd">
<td>elseOnSameLineAsClosingBrace</td>
<td>If <code>true</code>, then the else statement should be on the same
line the same as sameLine as closing brace (})</td>
<td>the same as <em>sameline</em></td>
</tr>
<tr class="even">
<td>elseOnSameLineAsOpeningBrace</td>
<td>If <code>true</code>, then the else statement should be on the same
line the same as sameLine as opening brace ({)</td>
<td>the same as <em>sameline</em></td>
</tr>
</tbody>
</table>

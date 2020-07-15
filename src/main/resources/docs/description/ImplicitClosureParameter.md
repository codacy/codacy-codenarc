*Since CodeNarc 1.5*

Checks for the implicit `it` closure parameter being used. Also checks
if an explicit `it` parameter has been specified.

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
<td>allowUsingItAsParameterName</td>
<td>To stop the rule reporting violations when an explicit closure parameter called <code>it</code> is used, set this property to <code>true</code>.</td>
<td><code>false</code></td>
</tr>
</tbody>
</table>

Example of violations:

``` 
    def closureWithViolation = { it * 10 }
    def closureWithViolationBecauseOfExplicitItParameter = { it -* it * 10}
```

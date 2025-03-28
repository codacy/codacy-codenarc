*Since CodeNarc 0.25*

Check whether list and map literals contain an optional trailing comma.
Rationale: Putting this comma in makes it easier to change the order of
the elements or add new elements on the end.

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
<td>checkList</td>
<td>To disable checking List literals, set this property to
<code>false</code></td>
<td><code>true</code></td>
</tr>
<tr>
<td>checkMap</td>
<td>To disable checking Map literals, set this property to
<code>false</code></td>
<td><code>true</code></td>
</tr>
<tr>
<td>ignoreSingleElementList</td>
<td>If true, skip checking Lists that have only a single element.</td>
<td><code>true</code></td>
</tr>
<tr>
<td>ignoreSingleElementMap</td>
<td>If true, skip checking Maps that have only a single element.</td>
<td><code>true</code></td>
</tr>
</tbody>
</table>

This is valid code:

      int[] array1 = []         // one line declaration
      int[] array2 = [          // empty list
                     ]
      int[] array3 = [1,2,3]    // one line declaration
      int[] array4 = [1,
                      2,
                      3,        // contains trailing comma
                     ]
      int[] array5 = [1,
                      2]        // last element followed by closing bracket on the same line
                                // NOTE: This style actually violates the intention of this rule

Example of violations:

      int[] array2 = [1,
                      2 // there is no trailing comma
                     ]

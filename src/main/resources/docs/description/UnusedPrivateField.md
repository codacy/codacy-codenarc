Checks for private fields that are not referenced within the same class.
Note that the `private` modifier is not currently “respected” by Groovy
code (i.e., Groovy can access `private` members within other classes).

By default, fields named `serialVersionUID`, and fields annotated with
`groovy.lang.Delegate` are ignored. The rule has a property named
*ignoreFieldNames*, which can be set to ignore other field names as
well. For instance, to also ignore fields named ‘fieldx’, set the
property to the ‘fieldx, serialVersionUID’

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
<td>ignoreFieldNames</td>
<td>Specifies one or more (comma-separated) field names that should be ignored (i.e., that should not cause a rule violation). The names may optionally contain wildcards (*,?).</td>
<td><code>serialVersionUID</code></td>
</tr>
<tr class="even">
<td>allowConstructorOnlyUsages</td>
<td>Should be set to <code>false</code> if violations are to be raised for fields which are used only within constructors.</td>
<td><code>true</code></td>
</tr>
</tbody>
</table>

Known limitations: \* Does not recognize field access when field name is
a GString (e.g. `this."${fieldName}"`) \* Does not recognize access of
private field of another instance (i.e. other than `this`)

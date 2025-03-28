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
<col style="width: 13%" />
<col style="width: 79%" />
<col style="width: 7%" />
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
<td>ignoreFieldNames</td>
<td>Specifies one or more (comma-separated) field names that should be
ignored (i.e., that should not cause a rule violation). The names may
optionally contain wildcards (*,?).</td>
<td><code>serialVersionUID</code></td>
</tr>
<tr>
<td>ignoreClassesAnnotatedWithNames</td>
<td>Specifies one or more (comma-separated) annotation names; any
classes annotated with those should be ignored (i.e., should not cause a
rule violation). The names may optionally contain wildcards (*,?).</td>
<td><code>Entity</code></td>
</tr>
<tr>
<td>allowConstructorOnlyUsages</td>
<td>Should be set to <code>false</code> if violations are to be raised
for fields which are used only within constructors.</td>
<td><code>true</code></td>
</tr>
</tbody>
</table>

Known limitations: \* Does not recognize field access when field name is
a GString (e.g. `this."${fieldName}"`) \* Does not recognize access of
private field of another instance (i.e. other than `this`)

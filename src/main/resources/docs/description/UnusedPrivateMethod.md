Checks for private methods that are not referenced within the same
class. Note that the `private` modifier is not currently “respected” by
Groovy code (i.e., Groovy can access `private` members within other
classes).

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
<td>ignoreMethodsWithAnnotationNames</td>
<td>Specifies one or more (comma-separated) annotation names that mark private methods that should be ignored (i.e., that should not cause a rule violation). The names may optionally contain wildcards (*,?).</td>
<td>’’</td>
</tr>
</tbody>
</table>

Known limitations: \* Does not recognize method reference through
property access (e.g. `getName()` accessed as `x.name`) \* Does not
recognize method invocations when method name is a GString
(e.g. `this."${methodName}"()`) \* Does not recognize invoking private
method of another instance (i.e. other than `this`) \* Does not
differentiate between multiple private methods with the same name but
different parameters (i.e., overloaded) \* Does not check for unused
constructors

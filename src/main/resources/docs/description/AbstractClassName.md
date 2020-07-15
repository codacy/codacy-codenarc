Verifies that the name of an abstract class matches the regular
expression specified in the **regex** property. If that property is null
or empty, then this rule is not applied (i.e., it does nothing). It
defaults to null, so this rule must be explicitly configured to be
active. This rule ignores interfaces and is applied only to abstract
classes.

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
<td>Specifies the regular expression used to validate the abstract class name. If null or empty, then this rule does nothing.</td>
<td><code>null</code></td>
</tr>
</tbody>
</table>

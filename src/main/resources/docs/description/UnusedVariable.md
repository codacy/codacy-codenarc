Checks for variables that are never referenced. An assignment to the
variable is not considered a reference.

The rule has a property named ignoreVariableNames, which can be set to
ignore some variable names. For instance, to ignore fields named
‘unused’, set the property to ‘unused’.

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
<td>ignoreVariableNames</td>
<td>Specifies one or more (comma-separated) variable names that should
be ignored (i.e., that should not cause a rule violation). The names may
optionally contain wildcards (*,?).</td>
<td><code>null</code></td>
</tr>
</tbody>
</table>

Known limitations: \* Incorrectly considers a variable referenced if
another variable with the same name is referenced elsewhere (in another
scope/block).

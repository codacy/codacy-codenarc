*Since CodeNarc 0.11*

This rule checks for duplicate number literals within the current class.

Code containing duplicate *Number* literals can usually be improved by
declaring the *Number* as a constant field.

By default, the rule does not analyze test files. This rule sets the
default value of the *doNotApplyToFilesMatching* property to ignore file
names ending in ‘Spec.groovy’, ‘Test.groovy’, ‘Tests.groovy’ or
‘TestCase.groovy’.

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
<td>ignoreNumbers</td>
<td>The optional comma-separated list of numbers that should be ignored
(i.e., not cause a violation).</td>
<td><code>0,1</code></td>
</tr>
<tr>
<td>duplicateNumberMinimumValue</td>
<td>Ignore duplicate numbers less than this value</td>
<td><code>null</code></td>
</tr>
</tbody>
</table>

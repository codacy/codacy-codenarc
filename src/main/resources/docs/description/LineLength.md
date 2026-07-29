*Since CodeNarc 0.15*

Checks the maximum length for each line of source code. It checks for
number of characters, so lines that include tabs may appear longer than
the allowed number when viewing the file. The maximum line length can be
configured by setting the length property, which defaults to 120.

NOTE: This rule does not support the @SuppressAnnotations annotation or
the classname-based rule properties (applyToClassNames,
doNotApplyToClassNames) to enable/disable the rule. If you want to
specify or restrict where this rule is applied, you must use the
file-based rule properties: applyToFileNames, doNotApplyToFileNames,
applyToFilesMatching and doNotApplyToFilesMatching.

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
<td>length</td>
<td>The maximum line length allowed.</td>
<td>120</td>
</tr>
<tr class="even">
<td>ignoreImportStatements</td>
<td>If <code>true</code>, then do not apply this rule to import
statements.</td>
<td><code>true</code></td>
</tr>
<tr class="odd">
<td>ignorePackageStatements</td>
<td>If <code>true</code>, then do not apply this rule to package
statements.</td>
<td><code>true</code></td>
</tr>
<tr class="even">
<td>ignoreLineRegex</td>
<td>If specified, then ignore lines matching this regular
expression.</td>
<td><code>null</code></td>
</tr>
</tbody>
</table>

*Since CodeNarc 0.14*

Checks for reference to any of the packages configured in
`packageNames`.

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
<td>packageNames</td>
<td>Specifies the comma-separated list of package names. The package
name(s) may optionally include wildcard characters (‘<em>’ or ’?’). Note
that the ’</em>’ wildcard matches any sequence of zero or more
characters in the package name, e.g. ’a.*’ matches ‘a.b’ as well as
‘a.b.c.d’. If <code>packageNames</code> is null or empty, do
nothing.</td>
<td><code>null</code></td>
</tr>
</tbody>
</table>

Note that you can use the standard rule properties, such as
`applyToClassNames`, `doNotApplyToFileNames` and `applyToFilesMatching`
to only apply this rule to a subset of all classes/files. These rule
properties are described in \[Standard Properties for Configuring
Rules\](./codenarc-configuring-rules.htm

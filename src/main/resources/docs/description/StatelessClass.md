Checks for non-`final` fields on a class. The intent of this rule is to
check a configured set of classes that should remain “stateless” and
reentrant. One example might be Grails service classes which are
singletons, by default, and so they should be reentrant.

This rule ignores `final` fields (either instance or static). Fields
that are `static` and non-`final`, however, do cause a violation.

This rule also ignores all classes annotated with the `@Immutable`
transformation. See
<http://groovy.codehaus.org/Immutable+transformation>.

This rule also ignores all fields annotated with the `@Inject` or
`@Value` annotations.

You can configure this rule to ignore certain fields either by name or
by type. This can be useful to ignore fields that hold references to
(static) dependencies (such as DAOs or Service objects) or static
configuration.

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
<td><code>null</code></td>
</tr>
<tr class="even">
<td>addToIgnoreFieldNames</td>
<td>Specifies one or more (comma-separated) field names to be added to the <code>ignoreFieldNames</code> property value. This is a special write-only property, and each call to <code>setAddIgnoreFieldNames()</code> adds to (rather than overwrites) the list of field names to be ignored.</td>
<td><code>null</code></td>
</tr>
<tr class="odd">
<td>ignoreFieldTypes</td>
<td>Specifies one or more (comma-separated) field types that should be ignored (i.e., that should not cause a rule violation). The names may optionally contain wildcards (*,?).</td>
<td><code>null</code></td>
</tr>
</tbody>
</table>

Note that you can use the standard rule properties, such as
`applyToClassNames`, `doNotApplyToFileNames` and `applyToFilesMatching`
to only apply this rule to a subset of all classes/files. These rule
properties are described in
zzz./codenarc-configuring-rules.html\#standard-properties-for-configuring-rules}
Standard Properties for Configuring Rulesyy.

Verifies that the name of each variable matches a regular expression. By
default, this rule checks that variable names start with a lowercase
letter and contain only letters or numbers.

Variables annotated with @Field are ignored.

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
<td>regex</td>
<td>Specifies the regular expression used to validate the variable name.
It is required and cannot be null or empty.</td>
<td>[a-z][a-zA-Z0-9]*</td>
</tr>
<tr>
<td>finalRegex</td>
<td>Specifies the regular expression used to validate <code>final</code>
variable names. It is optional. If not set, then <strong>regex</strong>
is used to validate <code>final</code> variable names.</td>
<td><code>null</code></td>
</tr>
<tr>
<td>ignoreVariableNames</td>
<td>Specifies one or more (comma-separated) variable names that should
be ignored (i.e., that should not cause a rule violation). The names may
optionally contain wildcards (*,?).</td>
<td><code>null</code></td>
</tr>
</tbody>
</table>

NOTE: Until CodeNarc 2.0, the default naming pattern for `final`
variable names was that they start with an uppercase letter and contain
only uppercase letters, numbers and underscores (i.e., like
*constants*). Starting with CodeNarc 2.0, that has been changed so that
`finalRegex` defaults to `null` and thus `final` variable names are
treated like regular variables. See

Verifies that the name of each field matches a regular expression. By
default it checks that fields that are not *static final* have field
names that start with a lowercase letter and contains only letters or
numbers. By default, *static final* field names start with an uppercase
letter and contain only uppercase letters, numbers and underscores.

**NOTE:** This rule checks only regular *fields* of a class, not
*properties*. In Groovy, *properties* are fields declared with no access
modifier (public, protected, private). Thus, this rule only checks
fields that specify an access modifier. For naming of *properties*, see
`PropertyNameRule`.

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
<td>Specifies the default regular expression used to validate the field
name. It is required and cannot be null or empty.</td>
<td>[a-z][a-zA-Z0-9]*</td>
</tr>
<tr>
<td>finalRegex</td>
<td>Specifies the regular expression used to validate <code>final</code>
field names. It is optional. If not set, then <code>final</code> fields
that are non-<code>static</code> are validated using
<strong>regex</strong>.</td>
<td><code>null</code></td>
</tr>
<tr>
<td>staticRegex</td>
<td>Specifies the regular expression used to validate
<code>static</code> field names. It is optional. If not set, then
<code>static</code> fields that are non-<code>final</code> are validated
using <strong>regex</strong>.</td>
<td><code>null</code></td>
</tr>
<tr>
<td>staticFinalRegex</td>
<td>Specifies the regular expression used to validate
<code>static final</code> field names. It is optional. If not set, then
<code>static final</code> fields are validated using
<strong>finalRegex</strong>, <strong>staticRegex</strong> or
<strong>regex</strong>.</td>
<td>[A-Z][A-Z0-9_]*</td>
</tr>
<tr>
<td>ignoreFieldNames</td>
<td>Specifies one or more (comma-separated) field names that should be
ignored (i.e., that should not cause a rule violation). The names may
optionally contain wildcards (*,?).</td>
<td><code>serialVersionUID</code></td>
</tr>
</tbody>
</table>

The order of precedence for the regular expression properties is:
**staticFinalRegex**, **finalRegex**, **staticRegex** and finally
**regex**. In other words, the first regex in that list matching the
modifiers for the field is the one that is applied for the field name
validation.

**NOTE:** This rule has been disabled by default (i.e., by setting its
*enabled* property to *false*). Given that Grails 2.x allows and
encourages controller actions to be defined as methods instead of
closures, this rule makes no sense for Grails 2.x projects.

Rule that checks for public methods on Grails controller classes. Static
methods are ignored.

Grails controller actions and interceptors are defined as properties on
the controller class. Public methods on a controller class are
unnecessary. They break encapsulation and can be confusing.

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
<td>ignoreMethodNames</td>
<td>Specifies one or more (comma-separated) method names that should be ignored (i.e., that should not cause a rule violation). The names may optionally contain wildcards (*,?).</td>
<td><code>null</code></td>
</tr>
</tbody>
</table>

This rule sets the default value of `applyToFilesMatching` to only match
files under the ‘grails-app/controllers’ folder. You can override this
with a different regular expression value if appropriate.

This rule also sets the default value of `applyToClassNames` to only
match class names ending in ‘Controller’. You can override this with a
different class name pattern (String with wildcards) if appropriate.

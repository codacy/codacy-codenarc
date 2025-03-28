*Since CodeNarc 0.19*

Checks for classes containing fields/properties/methods matching
configured illegal member modifiers or not matching any of the
configured allowed member modifiers.

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
<td>allowedFieldModifiers</td>
<td>Specifies one or more groups of whitespace-delimited modifier names
(e.g. “public static” or “protected”). Multiple groups are separated by
commas (e.g. “private final, protected”). If a field does not match all
of the modifiers in any group, then trigger a violation. If
<code>null</code> or empty, skip this check.</td>
<td><code>null</code></td>
</tr>
<tr>
<td>allowedMethodModifiers</td>
<td>Specifies one or more groups of whitespace-delimited modifier names
(e.g. “public static” or “protected”). Multiple groups are separated by
commas (e.g. “private final, protected”). If a method does not match all
of the modifiers in any group, then trigger a violation. If
<code>null</code> or empty, skip this check.</td>
<td><code>null</code></td>
</tr>
<tr>
<td>allowedPropertyModifiers</td>
<td>Specifies one or more groups of whitespace-delimited modifier names
(e.g. “public static” or “protected”). Multiple groups are separated by
commas (e.g. “private final, protected”). If a property does not match
all of the modifiers in any group, then trigger a violation. If
<code>null</code> or empty, skip this check.</td>
<td><code>null</code></td>
</tr>
<tr>
<td>ignoreMethodNames</td>
<td>Specifies one or more (comma-separated) method names that should be
ignored (i.e., that should not cause a rule violation). The names may
optionally contain wildcards (*,?).</td>
<td><code>null</code></td>
</tr>
<tr>
<td>ignoreMethodsWithAnnotationNames</td>
<td>Specifies one or more (comma-separated) annotation names that should
be ignored (i.e., methods with those annotations should not cause a rule
violation). The names may optionally contain wildcards (*,?). (Do not
include the “@” in the annotation name.</td>
<td><code>null</code></td>
</tr>
<tr>
<td>illegalFieldModifiers</td>
<td>Specifies one or more groups of whitespace-delimited modifier names
(e.g. “public static” or “protected”). Multiple groups are separated by
commas (e.g. “private final, protected”). If a field matches all of the
modifiers in any group, then trigger a violation. If <code>null</code>
or empty, skip this check.</td>
<td><code>null</code></td>
</tr>
<tr>
<td>illegalMethodModifiers</td>
<td>Specifies one or more groups of whitespace-delimited modifier names
(e.g. “public static” or “protected”). Multiple groups are separated by
commas (e.g. “private final, protected”). If a method matches all of the
modifiers in any group, then trigger a violation. If <code>null</code>
or empty, skip this check.</td>
<td><code>null</code></td>
</tr>
<tr>
<td>illegalPropertyModifiers</td>
<td>Specifies one or more groups of whitespace-delimited modifier names
(e.g. “public static” or “protected”). Multiple groups are separated by
commas (e.g. “private final, protected”). If a property matches all of
the modifiers in any group, then trigger a violation. If
<code>null</code> or empty, skip this check.</td>
<td><code>null</code></td>
</tr>
</tbody>
</table>

Modifiers for fields and methods include: \* public \* protected \*
private \* static \* final \* volatile (fields only) \* transient
(fields only)

Modifiers for properties are only: \* static \* final

Note that you must use the standard rule properties, such as
`applyToClassNames`, `doNotApplyToFileNames` and `applyToFilesMatching`
to apply this rule to a subset of all classes/files. These rule
properties are described in \[Standard Properties for Configuring
Rules\](./codenarc-configuring-rules.htm

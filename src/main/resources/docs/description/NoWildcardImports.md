*Since CodeNarc 0.21*

Checks for wildcard (star) imports. If the *ignoreStaticImports*
property is *true*, then do not check static imports. Similarly, do not
check the standard imports if ignoreImports is *true*.

Example of violations:

        import static foo.bar.*         // violation (unless ignoreStaticImports is true)
        import my.something.*           // violation (unless ignoreImports is true)

        public class MyClass{}

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
<td>ignoreStaticImports</td>
<td>If <em>true</em>, then do not check static imports.</td>
<td><code>false</code></td>
</tr>
<tr>
<td>ignoreImports</td>
<td>If <em>true</em>, then do not check imports.</td>
<td><code>false</code></td>
</tr>
</tbody>
</table>

NOTE: This is a file-based rule, rather than a typical AST-based rule,
so the *applyToClassNames* and *doNotApplyToClassNames* rule
configuration properties are not available. See [Standard Properties for
Configuring
Rules](./codenarc-configuring-rules.html#standard-properties-for-configuring-rules).

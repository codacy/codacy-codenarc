*Since CodeNarc 0.22*

A package source file’s path should match the package declaration.

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
<td>groupId</td>
<td>Specifies the common <em>group id</em> part of a package name, that will appear within all checked package names. It must also map to the file path for the corresponding source file. <br/><br/> For instance, a <em>groupId</em> of <em>“org.sample”</em> means that for all classes that specify a package, that package name must include <em>“org.sample”</em>, and the source file must exist under an “org/sample” directory. Then, a <code>MyClass</code> class in a <code>org.sample.util</code> package must be defined in a “MyClass.groovy” file within a <em>“org/sample/util”</em> directory. That directory can be the child of any arbitrary <em>root path</em>, e.g. “src/main/groovy”.<br/><br/> To find the sub-path relevant for the package the rule searches for the first appearance of <em>groupId</em> in the file path. It’s <em>required</em> to configure this. <br/><br/> If <code>groupId</code> is null or empty, this rule does nothing.</td>
<td><code>null</code></td>
</tr>
</tbody>
</table>

NOTE: This is a file-based rule, rather than an AST-based rule, so the
*applyToClassNames* and *doNotApplyToClassNames* rule configuration
properties are not available. See [Standard Properties for Configuring
Rules](./codenarc-configuring-rules.html#standard-properties-for-configuring-rules).

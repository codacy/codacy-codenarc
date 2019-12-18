
*Since CodeNarc 0.22*

A package source file's path should match the package declaration.

| Property                    | Description            | Default Value    |
|-----------------------------|------------------------|------------------|
| groupId                     | Specifies the common *group id* part of a package name, that will appear within all checked package names. It must also map to the file path for the corresponding source file. <br/><br/> For instance, a *groupId* of *"org.sample"* means that for all classes that specify a package, that package name must include *"org.sample"*, and the source file must exist under an "org/sample" directory. Then, a `MyClass` class in a `org.sample.util` package must be defined in a "MyClass.groovy" file within a *"org/sample/util"* directory. That directory can be the child of any arbitrary *root path*, e.g. "src/main/groovy".<br/><br/> To find the sub-path relevant for the package the rule searches for the first appearance of *groupId* in the file path. It's *required* to configure this. <br/><br/> If `groupId` is null or empty, this rule does nothing. | `null` |

NOTE: This is a file-based rule, rather than an AST-based rule, so the *applyToClassNames* and
*doNotApplyToClassNames* rule configuration properties are not available. See
[Standard Properties for Configuring Rules](./codenarc-configuring-rules.html#standard-properties-for-configuring-rules).


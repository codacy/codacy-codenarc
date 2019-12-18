
*Since CodeNarc 0.14*

Checks for reference to any of the packages configured in `packageNames`.

| Property                    | Description            | Default Value    |
|-----------------------------|------------------------|------------------|
| packageNames                | Specifies the comma-separated list of package names. The package name(s) may optionally include wildcard characters ('*' or '?'). Note that the '*' wildcard matches any sequence of zero or more characters in the package name, e.g. 'a.*' matches 'a.b' as well as 'a.b.c.d'. If `packageNames` is null or empty, do nothing. | `null` |

Note that you can use the standard rule properties, such as `applyToClassNames`, `doNotApplyToFileNames`
and `applyToFilesMatching` to only apply this rule to a subset of all classes/files. These rule properties
are described in 
[Standard Properties for Configuring Rules](./codenarc-configuring-rules.htm
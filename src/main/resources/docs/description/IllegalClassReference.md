*Since CodeNarc 0.15*

Checks for reference to any of the classes configured in `classNames`.

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
<td>classNames</td>
<td>Specifies the comma-separated list of (fully-qualified) class names. The class name(s) may optionally include wildcard characters (‘<em>’ or ’?’). Note that the ’</em>’ wildcard matches any sequence of zero or more characters in the class/package name, e.g. ’a.*.MyClass’ matches <code>a.b.MyClass</code> as well as <code>a.b.c.d.MyClass</code>. If <code>classNames</code> is null or empty, do nothing.</td>
<td><code>null</code></td>
</tr>
</tbody>
</table>

Note that you can use the standard rule properties, such as
`applyToClassNames`, `doNotApplyToFileNames` and `applyToFilesMatching`
to only apply this rule to a subset of all classes/files. These rule
properties are described in [Standard Properties for Configuring
Rules](./codenarc-configuring-rules.html#standard-properties-for-configuring-rules).

This rule can be useful for governance and enforcement of *architectural
layering*. For instance, making sure that view or model classes, for
instance, do not contain references to DAO classes (e.g., \*Dao).

Here is an example configuration of this rule used to ensure that DAO
classes are not referenced from within model classes:

``` 
    ruleset {
        description "Example CodeNarc Ruleset"

        // ...

        IllegalClassReference {
            name = 'DoNotReferenceDaoFromModelClasses'
            priority = 2
            classNames = '*Dao'
            applyToClassNames = 'com.example.model.*'
            description = 'Do not reference DAOs from model classes.'
        }
    }
```

A RuleSet can contain any number of instances of this rule, but each
should be configured with a unique rule *name* and *classNames*, and
(optionally) customized *violationMessage* and *priority*.

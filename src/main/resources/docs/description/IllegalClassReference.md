
*Since CodeNarc 0.15*

Checks for reference to any of the classes configured in `classNames`.

| Property                    | Description            | Default Value    |
|-----------------------------|------------------------|------------------|
| classNames                  | Specifies the comma-separated list of (fully-qualified) class names. The class name(s) may optionally include wildcard characters ('*' or '?'). Note that the '*' wildcard matches any sequence of zero or more characters in the class/package name, e.g. 'a.*.MyClass' matches `a.b.MyClass` as well as `a.b.c.d.MyClass`. If `classNames` is null or empty, do nothing. | `null` |

Note that you can use the standard rule properties, such as `applyToClassNames`, `doNotApplyToFileNames`
and `applyToFilesMatching` to only apply this rule to a subset of all classes/files. These rule properties
are described in 
[Standard Properties for Configuring Rules](./codenarc-configuring-rules.html#standard-properties-for-configuring-rules).

This rule can be useful for governance and enforcement of *architectural layering*. For instance,
making sure that view or model classes, for instance, do not contain references to DAO classes (e.g., *Dao).

Here is an example configuration of this rule used to ensure that DAO classes are not referenced from
within model classes:

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

A RuleSet can contain any number of instances of this rule, but each should be configured
with a unique rule *name* and *classNames*, and (optionally) customized *violationMessage* and *priority*.

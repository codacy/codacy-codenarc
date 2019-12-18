
*Since CodeNarc 0.11*

This rule checks for duplicate String literals within the current class.

Code containing duplicate *String* literals can usually be improved by declaring the *String* as a constant field.

By default, the rule does not analyze test files. This rule sets the default value of the
*doNotApplyToFilesMatching* property to ignore file names ending in 'Spec.groovy', 'Test.groovy', 'Tests.groovy'
or 'TestCase.groovy'.

| Property                    | Description            | Default Value    |
|-----------------------------|------------------------|------------------|
| ignoreStrings               | The optional comma-separated list of Strings that should be ignored (i.e., not cause a violation). | `''` (empty string) |



*Since CodeNarc 0.11*

This rule detects JUnit assertions in object equality. These assertions should be made by more specific methods,
like `assertEquals`.

This rule sets the default value of the *applyToClassNames* property to only match class names
ending in 'Spec', 'Test', 'Tests' or 'TestCase'.


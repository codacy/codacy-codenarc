
Rule that checks that if the JUnit `tearDown` method is defined, that it includes a call to
`super.tearDown()`.

This rule ignored methods annotated with `@After` or `@AfterClass`.

This rule sets the default value of the *applyToClassNames* property to only match class names
ending in 'Spec', 'Test', 'Tests' or 'TestCase'.

Rule that checks that if the JUnit `setUp` method is defined, that it
includes a call to `super.setUp()`.

This rule ignored methods annotated with `@Before` or `@BeforeClass`.

This rule sets the default value of the *applyToClassNames* property to
only match class names ending in ‘Spec’, ‘Test’, ‘Tests’ or ‘TestCase’.

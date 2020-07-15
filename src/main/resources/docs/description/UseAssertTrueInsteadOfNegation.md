*Since CodeNarc 0.12*

In unit tests, if a condition is expected to be true then there is no
sense using `assertFalse` with the negation operator. For instance,
`assertFalse(!condition)` can always be simplified to
`assertTrue(condition)`.

This rule sets the default value of the *applyToClassNames* property to
only match class names ending in ‘Spec’, ‘Test’, ‘Tests’ or ‘TestCase’.

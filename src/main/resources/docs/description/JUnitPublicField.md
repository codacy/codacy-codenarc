*Since CodeNarc 0.19*

Checks for public fields on a JUnit test class. There is usually no
reason to have a public field (even a constant) on a test class.

Fields within interfaces and fields annotated with @Rule are ignored.

This rule sets the default value of the *applyToClassNames* property to
only match class names ending in ‘Spec’, ‘Test’, ‘Tests’ or ‘TestCase’.

Example of violations:

        import org.junit.Test
        class MyTestCase {
            public int count                        // violation
            public static final MAX_VALUE = 1000    // violation

            @Test
            void testMe() { }
        }

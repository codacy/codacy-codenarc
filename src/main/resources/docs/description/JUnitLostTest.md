*Since CodeNarc 0.18*

This rule checks for classes that import JUnit 4 classes and contain a
`public`, instance, `void`, no-arg method named \*test\*\* that is not
abstract and not annotated with the JUnit 4 `@Test` annotation.

Note: This rule should be disabled for Grails 2.x projects, since the
Grails test framework can use AST Transformations to automatically
annotate test methods.

This rule sets the default value of the *applyToClassNames* property to
only match class names ending in ‘Spec’, ‘Test’, ‘Tests’ or ‘TestCase’.

Example of violations:

        import org.junit.Test

        class MyTestCase {
            void testMe() { }           // missing @Test annotation
        }

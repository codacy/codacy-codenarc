Rule that checks for JUnit `tearDown()` methods that contain only a call
to `super.tearDown()`. The method is then unnecessary.

This rule sets the default value of the *applyToClassNames* property to
only match class names ending in ‘Spec’, ‘Test’, ‘Tests’ or ‘TestCase’.

Here is an example of a violation:

        class MyTest extends TestCase {
            void tearDown() {               // violation
                super.tearDown()
            }
        }

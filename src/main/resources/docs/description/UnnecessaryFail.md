*Since CodeNarc 0.13*

In a unit test, catching an exception and immediately calling
Assert.fail() is pointless and hides the stack trace. It is better to
rethrow the exception or not catch the exception at all.

This rule sets the default value of the *applyToClassNames* property to
only match class names ending in ‘Spec’, ‘Test’, ‘Tests’ or ‘TestCase’.

Example of violations:

``` 
    public void testSomething() {
        try {
            something()
        } catch (Exception e) {
            fail(e.message)
        }

        try {
            something()
        } catch (Exception e) {
            fail()
        }
    }
```

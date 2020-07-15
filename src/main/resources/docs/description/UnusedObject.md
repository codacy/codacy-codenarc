Checks for object allocations that are not assigned or used, unless it
is the last statement within a block (because it may be the intentional
return value). Examples include:

By default, this rule does not analyze test files. This rule sets the
default value of the *doNotApplyToFilesMatching* property to ignore file
names ending in ‘Spec.groovy,’‘Test.groovy’, ‘Tests.groovy’ or
‘TestCase.groovy’. Invoking constructors without using the result is a
common pattern in tests.

``` 
    int myMethod() {
        new BigDecimal("23.45")     // unused
        return -1
    }

    BigDecimal myMethod() {
        new BigDecimal("23.45")     // OK (last statement in block)
    }

    def closure = {
        doStuff()
        new Date()                  // unused
        doOtherStuff()
    }

    def closure = { new Date() }    // OK (last statement in block)
```

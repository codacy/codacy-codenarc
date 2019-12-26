
*Since CodeNarc 0.22*

Checks for use of the `instanceof` operator. Prefer using *polymorphism* instead.

Use the `ignoreTypeNames` property to configure ignored type names (the class name specified as the
right-hand expression of the `instanceof`). It defaults to ignoring `instanceof` checks against exception classes.

Here are a couple references that discuss the problems with using `instanceof` and the preference
for using *polymorphism* instead:

  * [Beware of instanceof operator](http://www.javapractices.com/topic/TopicAction.do?Id=31)
  * [How does one use polymorphism instead of instanceof? (And why?)](http://stackoverflow.com/questions/4192837/how-does-one-use-polymorphism-instead-of-instanceof-and-why)

By default, the rule does not analyze test files. This rule sets the default value of the
*doNotApplyToFilesMatching* property to ignore file names ending in 'Spec.groovy', 'Test.groovy', 'Tests.groovy'
or 'TestCase.groovy'.

| Property                    | Description            | Default Value    |
|-----------------------------|------------------------|------------------|
| ignoreTypeNames             | Specifies one or more (comma-separated) class names that should be ignored (i.e., that should not cause a rule violation). The names may optionally contain wildcards (*,?).  | "*Exceptions" |

  Example of violations:

```
    class MyClass {
        boolean isRunnable = this instanceof Runnable       // violation
    }
```


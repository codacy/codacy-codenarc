Checks for classes that implement the `java.lang.Cloneable` interface
without implementing the `clone()` method.

Here is an example of code that produces a violation:

``` 
    class BadClass implements Cloneable {
        def someMethod()
    }
```

This rule reports uses of the `Thread.yield()` method.

Method calls to `Thread.yield()` should not be allowed. This method has
no useful guaranteed semantics, and is often used by inexperienced
programmers to mask race conditions.

Here is an example of code that produces a violation:

``` 
     def method() {
         Thread.yield()
     }
```

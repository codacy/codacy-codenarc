Checks for throwing an exception from within a *finally* block. Throwing
an exception from a *finally* block is confusing and can hide the
original exception.

Here is an example of code that produces a violation:

``` 
    int myMethod() {
        try {
            doSomething()
            throw new Exception()
        } finally {
            println 'finally'
            throw new Exception()   // violation
        }
    }
```

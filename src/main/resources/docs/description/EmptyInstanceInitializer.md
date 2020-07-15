*Since CodeNarc 0.13*

An empty class instance initializer was found. It is safe to remove it.
Example:

``` 
    class MyClass {
        { }     // empty instance initializer, not a closure
    }
```

*Since CodeNarc 0.13*

An empty static initializer was found. It is safe to remove it. Example:

``` 
    class MyClass {
        static { }
    }
```

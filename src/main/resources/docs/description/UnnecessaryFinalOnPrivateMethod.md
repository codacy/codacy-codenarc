*Since CodeNarc 0.14*

A private method is marked final. Private methods cannot be overridden,
so marking it final is unnecessary.

Example of violations:

``` 
    private final method() {}
```

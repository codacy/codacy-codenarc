*Since CodeNarc 0.14*

If a method is called and the only parameter to that method is an inline
closure then the parentheses of the method call can be omitted.

Example of violations:

``` 
    [1,2,3].each() { println it }
```

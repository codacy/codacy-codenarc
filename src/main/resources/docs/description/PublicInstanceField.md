*Since CodeNarc 0.14*

Using public fields is considered to be a bad design. Use properties
instead.

Example of violations:

``` 
    class Person {
        public String name
    }
```

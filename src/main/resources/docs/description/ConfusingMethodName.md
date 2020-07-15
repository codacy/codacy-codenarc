*Since CodeNarc 0.11*

Checks for very confusing method names. The referenced methods have
names that differ only by capitalization. This is very confusing because
if the capitalization were identical then one of the methods would
override the other.

Also, violations are triggered when methods and fields have very similar
names.

``` 
    class MyClass {
        int total
        int total() {
            1
        }
    }
```

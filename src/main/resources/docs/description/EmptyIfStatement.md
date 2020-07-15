Checks for empty *if* statements. Empty *if* statements are confusing
and serve no purpose.

Here is an example of code that produces a violation:

``` 
    def myMethod() {
        if (x==23) {
            // empty
        }
    }
```

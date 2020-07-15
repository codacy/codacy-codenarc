Checks for empty *try* blocks. Empty *try* blocks are confusing and
serve no purpose.

Here is an example of code that produces a violation:

``` 
    def myMethod() {
        try {
            // empty
        } catch(MyException e) {
            e.printStackTrace()
        }
    }
```

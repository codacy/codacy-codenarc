

Checks for empty *else* blocks. Empty *else* blocks are confusing and serve no purpose.

Here is an example of code that produces a violation:

```
    def myMethod() {
        if (x==23) {
            println 'ok'
        } else {
            // empty
        }
    }
```

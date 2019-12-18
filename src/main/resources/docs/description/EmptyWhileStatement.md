
Checks for empty *while* statements. Empty *while* statements are confusing and serve no purpose.

Here is an example of code that produces a violation:

```
    def myMethod() {
        while (!stopped) {
            // empty
        }
    }
```



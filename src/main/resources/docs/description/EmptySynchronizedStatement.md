~

Checks for empty *synchronized* statements. Empty *synchronized* statements are confusing and serve no purpose.

Here is an example of code that produces a violation:

```
    class MyClass {
        def myMethod() {
            synchronized(lock) {
            }
        }
    }
```


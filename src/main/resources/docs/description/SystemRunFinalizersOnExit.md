

This rule reports uses of the `System.runFinalizersOnExit()` method.

Method calls to `System.runFinalizersOnExit()` should not be allowed. This method is inherently
non-thread-safe, may result in data corruption, deadlock, and may affect parts of the program
far removed from it's call point. It is deprecated, and it's use strongly discouraged.

Here is an example of code that produces a violation:

```
    def method() {
        System.runFinalizersOnExit(true)
    }
```


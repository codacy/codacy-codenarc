
*Since CodeNarc 0.14*
Detects code that catches java.lang.ThreadDeath without re-throwing it.

Example of violations:

```
    try {
        def a = 0
    } catch (ThreadDeath td) {
        td.printStackTrace()
    }
```



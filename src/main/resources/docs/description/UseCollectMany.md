
*Since CodeNarc 0.16*

In many case `collectMany()` yields the same result as `collect{}.flatten()`.
It is easier to understand and more clearly conveys the intent.

Example of violations:

```
def l = [1, 2, 3, 4]

l.collect{ [it, it*2] }.flatten() // suboptimal

l.collectMany{ [it, it*2] }       // same functionality, better readability
```
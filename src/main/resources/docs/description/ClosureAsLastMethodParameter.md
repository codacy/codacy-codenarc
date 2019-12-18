
*Since CodeNarc 0.14*

If a method is called and the last parameter is an inline closure then it can be declared outside
of the method call parentheses.

Example of violations:

```
    // creates violation: poor Groovy style
    [1,2,3].each({ println it })

    // no violation
    [1,2,3].each { println it }
```


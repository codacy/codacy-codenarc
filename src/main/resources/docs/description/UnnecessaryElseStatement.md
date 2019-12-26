
*Since CodeNarc 0.14*

When an `if` statement block ends with a `return` statement, then the `else` is unnecessary.
The logic in the `else` branch can be run without being in a new scope.

Example of violations:

```
    if(value){
        println 'Executing if logic...'
        return true
    } else {
        println 'Executing else logic...'
    }

    // can be replaced by:

    if(value){
        println 'Executing if logic...'
        return true
    }
    println 'Executing else logic...'
```


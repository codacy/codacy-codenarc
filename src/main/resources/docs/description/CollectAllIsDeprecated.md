*Since CodeNarc 0.16*

The `collectAll` method is deprecated since Groovy 1.8.1. Use
`collectNested` instead.

Example of violations:

``` 
    def list = [1, 2, [3, 4, [5, 6]], 7]

    list.collectAll { it * 2 }      // deprecated

    list.collectNested { it * 2 }   // replacement
```

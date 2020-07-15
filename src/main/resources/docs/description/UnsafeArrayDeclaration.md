*Since CodeNarc 0.14*

Triggers a violation when an array is declared public, final, and
static.

In most cases an array declared public, final and static is a bug.
Because arrays are mutable objects, the final constraint requires that
the array object itself be assigned only once, but makes no guarantees
about the values of the array elements. Since the array is public, a
malicious program can change the values stored in the array. In most
situations the array should be made private.

Example of violations:

``` 
    class MyClass {
        public static final String[] myArray = init()
        public static final def myArray = [] as String[]
    }
```

*Since CodeNarc 0.21*

Checks for `toString()` methods that return `null`. This is
unconventional and could cause unexpected `NullPointerExceptions` from
normal or implicit use of `toString()`.

Example of violations:

``` 
    class MyClass {
        String toString() {
            if (foo()) {
                return 'MyClass'
            } else {
                return null         // violation
            }
        }
    }

    class MyClass {
        String toString() {
            calculateStuff()
            null                    // violation
        }
    }

    class MyClass {
        String toString() {         // violation - implicit return of null
        }
    }
```

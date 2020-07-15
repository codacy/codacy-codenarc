*Since CodeNarc 0.13*

Method contains a pointless self-assignment to a variable or property.
Either the code is pointless or the equals()/get() method has been
overridden to have a side effect, which is a terrible way to code
getters and violates the contract of equals().

Examples:

``` 
    x = x               // violation
    def method(y) {
        y = y           // violation
    }
    a.b.c = a.b.c       // violation

    x = y               // acceptable
    a.b = a.zz          // acceptable
    a.b = a().b         // acceptable
```

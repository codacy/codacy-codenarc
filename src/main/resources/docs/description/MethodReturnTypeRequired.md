*Since CodeNarc 1.1*

Checks that method return types are not dynamic, that is they are
explicitly stated and different than def.

Example of violations:

``` 
    def methodWithDynamicReturnType() {    // violation
    }

    private methodWithoutReturnType() {    // violation
    }

    Object objectReturningMethod() {       // OK
    }
```


*Since CodeNarc 1.1*

Checks that method parameters are not dynamically typed, that is they are explicitly stated and different than def.

Example of violations:

```
    void methodWithDynamicParameter(def parameter) {              // violation
    }

    void methodWithParameterWithoutTypeDeclaration(parameter) {   // violation
    }

    void methodWithObjectParameter(Object parameter)              // OK
```


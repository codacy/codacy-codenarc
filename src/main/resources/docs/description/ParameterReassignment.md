
*Since CodeNarc 0.17*

Checks for a method or closure parameter being reassigned to a new value within the body of the method/closure,
which is a confusing and questionable practice. Use a temporary variable instead.

Example of violations:

```
    void myMethod(int a, String b) {
        println a
        b = 'new value'     // violation
    }

    def myClosure1 = { int a, b -*
        a = 123             // violation
    }
```


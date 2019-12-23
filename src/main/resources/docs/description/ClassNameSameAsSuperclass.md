
*Since CodeNarc 0.24*

Checks for any class that has an identical name to its superclass, other than the package. This can be very confusing.

Also see FindBugs NM_SAME_SIMPLE_NAME_AS_SUPERCLASS rule.

Example of violations:

```
    class MyClass extends other.MyClass         // violation
```


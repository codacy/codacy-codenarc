
*Since CodeNarc 0.15*

To make a reference to a class, it is unnecessary to specify the '.class' identifier. For instance String.class can be shortened to String.

Example of violations:

```
    // The '.class' identifier is unnecessary, violation occurs
    def x = String.class

    // Ok, unnecessary '.class' identifier has been excluded
    def x = String
```


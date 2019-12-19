
*Since CodeNarc 0.13*

This rule catches usages of java.lang.Integer.getInteger(String, ...) which reads an Integer from the System properties.
It is often mistakenly used to attempt to read user input or parse a String into an Integer.
It is a poor piece of API to use; replace it with System.properties['prop'].

Example of violations:

```
    // violations
    Integer.getInteger(value)
    Integer.getInteger(value, radix)

    // zero or more than 2 parameters is OK, must be different method
    Integer.getInteger()
    Integer.getInteger(value, radix, locale)
```


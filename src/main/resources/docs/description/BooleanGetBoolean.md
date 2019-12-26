
*Since CodeNarc 0.13*

This rule catches usages of java.lang.Boolean.getBoolean(String) which reads a boolean from the System properties. It is often mistakenly used to attempt to read user input or parse a String into a boolean. It is a poor piece of API to use; replace it with System.properties['prop̈́'].

Example of violations:

```
    // produces violation
    Boolean.getBoolean(value)

    // zero or two parameters is OK, must be different method
    Boolean.getBoolean(value, 1)
    Boolean.getBoolean()
```

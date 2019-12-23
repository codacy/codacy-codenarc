
*Since CodeNarc 0.17*

Checks for references to the (*effectively*) obsolete `java.util.Vector` class.
Use the **Java Collections Framework** classes instead, including `ArrayList` or
`Collections.synchronizedList()`. See the JDK javadoc.

Example of violations:

```
    def myList = new Vector()           // violation
```
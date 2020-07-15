*Since CodeNarc 0.17*

Checks for references to the (*effectively*) obsolete
`java.util.Hashtable` class. Use the **Java Collections Framework**
classes instead, including `HashMap` or `ConcurrentHashMap`. See the JDK
javadoc.

Example of violations:

``` 
    def myMap = new Hashtable()           // violation
```

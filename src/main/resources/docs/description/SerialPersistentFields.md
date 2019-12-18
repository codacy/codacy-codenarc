
*Since CodeNarc 0.14*

To use a **Serializable** object's `serialPersistentFields` correctly, it must be declared `private`, `static`,
and `final`.

The Java Object Serialization Specification allows developers to manually define `Serializable` fields for a
class by specifying them in the `serialPersistentFields` array. This feature will only work if
`serialPersistentFields` is declared as `private`, `static`, and `final`. Also, specific to Groovy,
the field must be of type `ObjectStreamField[]`, and cannot be `Object`.

References:

    * Standards Mapping - Common Weakness Enumeration - (CWE) CWE ID 485

    * Sun Microsystems, Inc. Java Sun Tutorial

    []

Example of violations:

```
    class MyClass implements Serializable {
        public ObjectStreamField[] serialPersistentFields = [ new ObjectStreamField("myField", List.class) ] as ObjectStreamField[]
    }

    // the JVM sees the field type as Object, which won't work
    class MyOtherClass implements Serializable {
        private static final serialPersistentFields = [ new ObjectStreamField("myField", List.class) ] as ObjectStreamField[]
    }
```



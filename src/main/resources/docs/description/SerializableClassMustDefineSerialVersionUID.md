
*Since CodeNarc 0.13*

Classes that implement `Serializable` should define a `serialVersionUID`. Deserialization uses this number
to ensure that a loaded class corresponds exactly to a serialized object. If you don't define serialVersionUID, the
system will make one by hashing most of your class's features. Then if you change anything, the UID will change and
Java won't let you reload old data.

An example of a missing serialVersionUID:

```
    class MyClass implements Serializable {
        // missing serialVersionUID
    }
```

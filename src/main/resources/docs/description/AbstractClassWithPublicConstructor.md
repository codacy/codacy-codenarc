
*Since CodeNarc 0.14*

Checks for `abstract` classes that define a `public` constructor, which is useless and confusing.

The following code produces a violation:

```
    abstract class MyClass {
        MyClass() { }
    }
```



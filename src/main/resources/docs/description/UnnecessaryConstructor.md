
*Since CodeNarc 0.11*

This rule detects when a constructor is not necessary; i.e., when there's only one constructor, it's
`public`, has an empty body, and takes no arguments, or else contains only a single call to `super()`.

| Property                    | Description            | Default Value    |
|-----------------------------|------------------------|------------------|
| ignoreAnnotations | If `true`, then do not report violations if a constructor has one or more annotations. | `false` |

Example of violations:

```
    class MyClass {
        public MyClass() {          // violation; constructor is not necessary
        }
    }

    class MyClass2 extends OtherClass {
        MyClass2() {                // violation; constructor is not necessary
            super()
        }
    }
```


*Since CodeNarc 0.13*

The ‘public’ modifier is not required on methods, constructors or
classes.

Because of Groovy parsing limitations, this rule ignores methods (and
constructors) that include Generic types in the method declaration.

Example of violations:

``` 
    // violation on class
    public class MyClass {
        // violation on constructor
        public MyClass() {}

        // violation on method
        public void myMethod() {}
    }
```

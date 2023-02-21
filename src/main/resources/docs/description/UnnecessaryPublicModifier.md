*Since CodeNarc 0.13*

The ‘public’ modifier is not required on methods, constructors or
classes.

Limitations: \* Because of Groovy parsing limitations, this rule ignores
methods (and constructors) that include Generic types in the method
declaration. \* This rule will not catch the `public` modifier if it is
on a separate line from the rest of the declaration.

Example of violations:

        public class MyClass {              // violation on class
            public MyClass() {}             // violation on constructor
            public void myMethod() {}       // violation on method
        }

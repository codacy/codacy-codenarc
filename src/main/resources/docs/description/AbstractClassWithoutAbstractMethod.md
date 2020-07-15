*Since CodeNarc 0.12*

The abstract class does not contain any abstract methods. An abstract
class suggests an incomplete implementation, which is to be completed by
subclasses implementing the abstract methods. If the class is intended
to be used as a base class only (not to be instantiated directly) a
protected constructor can be provided prevent direct instantiation.

Example:

``` 
    public abstract class MyBaseClass {
        void method1() {  }
        void method2() {  }
        // consider using abstract methods or removing
        // the abstract modifier and adding protected constructors
    }
```

The following examples all pass:

``` 
    abstract class MyClass extends AbstractParent {
        // OK because parent is named Abstract.*
    }
    abstract class MyClass extends BaseParent{
        // OK because parent is named Base.*
    }
```

This rule has a single `enhancedMode` property which defaults to
`false`. When set to `true`, this rule will run in [enhanced
mode](./codenarc-enhanced-classpath-rules.html) and will not produce a
violation when an abstract class extends an abstract superclass.

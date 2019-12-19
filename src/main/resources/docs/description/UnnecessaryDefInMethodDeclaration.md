
*Since CodeNarc 0.13*

If a method has a visibility modifier or a type declaration, then the def keyword is unneeded.
For instance 'def private method() {}' is redundant and can be simplified to 'private method() {}'.

Examples of violations:

```
    // def and private is redundant
    def private method1() { return 4 }

    // def and protected is redundant
    def protected method2() { return 4 }

    // def and public is redundant
    def public method3() { return 4 }

    // def and static is redundant
    def static method4() { return 4 }

    // def and type is redundant
    def Object method5() { return 4 }

    class MyClass {
        def MyClass() {}    // def is redundant
    }
```


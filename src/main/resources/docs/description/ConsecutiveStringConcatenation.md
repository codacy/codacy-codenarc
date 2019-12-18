
*Since CodeNarc 0.13*

Catches concatenation of two string literals on the same line. These can safely by joined. In Java, the Java compiler
will join two String literals together and place them in the Constant Pool. However, Groovy will not because the plus()
method may override the + operator.

Examples:

```
    // Violations
    def a = 'Hello' + 'World'   // should be 'HelloWorld'
    def b = "$Hello" + 'World'  // should be "${Hello}World"
    def c = 'Hello' + "$World"  // should be "Hello${World}"
    def d = 'Hello' + 5         // should be 'Hello5'
    def e = 'Hello' + '''
                        world   // should be joined
                      '''
    def f = '''Hello
                  ''' + 'world'   // should be joined


    // Not Violations
    def g = 'Hello' +           // OK because of line break
                'World'
    def h = 'Hello' + null      // OK because not a string
    def i = 'Hello' + method()  // OK because not a string
    def j = 'Hello' - "$World"  // OK because not +
```


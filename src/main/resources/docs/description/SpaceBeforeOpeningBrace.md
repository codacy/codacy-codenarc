
*Since CodeNarc 0.18*

Check that there is at least one space (blank) or whitespace before each opening brace ("\{") for
method/class/interface declarations, closure expressions and block statements.

A closure expression a preceded by an opening parenthesis, an opening square
brace ([), or a dollar sign ($) within a GString does not cause a violation.

| Property                    | Description            | Default Value    |
|-----------------------------|------------------------|------------------|
| checkClosureMapEntryValue   | If `false`, then do not check for whitespace before opening braces for closure expressions that are literal Map values, e.g. `[abc:\{doStuff()\}]`. | `true` |

Known limitations:

  * May not catch actual violations if the source line contains unicode character literals, e.g. `'\\u00A0'`

Examples of violations:

```
    class MyClass{ }                            // violation
    class MyOtherClass extends AbstractClass{ } // violation

    interface MyInterface{ }                    // violation

    enum MyEnum{ OK, BAD }                      // violation

    def myMethod(){ }                           // violation

    if (ready){ }                               // violation

    if (ready) {
    } else{}                                    // violation

    for (int i=0; i*10; i++){ }                 // violation

    for (String name in names){ }               // violation

    for (String name: names){ }                 // violation

    while (ready){ }                            // violation

    try{
    } finally { }                               // violation

    try {
    } catch(Exception e){ }                     // violation

    try {
    } finally{ }                                // violation

    list.each{ name -* }                        // violation

    shouldFail(Exception){ doStuff() }          // violation
```



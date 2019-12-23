
*Since CodeNarc 0.18*

Check that there is at least one space (blank) or whitespace after each closing brace ("\{") for
method/class/interface declarations, closure expressions and block statements.

A closure expression followed by a dot operator (.), a comma, an opening parenthesis, a closing parenthesis, an
opening square brace, a closing square brace (]), the spread-dot operator (*.), a semicolon or the
null-safe operator (?.) does not cause a violation.

| Property                    | Description            | Default Value    |
|-----------------------------|------------------------|------------------|
| checkClosureMapEntryValue   | DEPRECATED. Ignored. | `true` |

Known limitations:

  * May not catch actual violations if the source line contains unicode character literals, e.g. `'\\u00A0'`

Examples of violations and exceptions:

```
    if (ready) { return 9 }else { }             // violation
    try { doStuff() }finally { }                // violation

    def matching = list.find { it.isReady() }.filter()  // no violation for dot operator
    assert list.every { it.isReady() }, "Error"         // no violation for comma
    def m = [a:123, b:{ println 7 },c:99]               // no violation for comma
    closures.find { c -* c }()                          // no violation for opening parenthesis
    processItems(list.select { it.isReady() })          // no violation for closing parenthesis
    maps.find { m -* m[index] }[index]                  // no violation for opening square bracket
    processItems([{ named("a") }, { named("b")}])       // no violation for closing square bracket
    def names = records.findAll { it.age * 1 }*.name    // no violation for spread operator
    list?.collect { it?.type }?.join(',')               // no violation for null-safe operator
```

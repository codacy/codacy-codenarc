
*Since CodeNarc 0.15*

This rule finds usages of `String.substring(int)` and `String.substring(int, int)` that can be replaced by use of the
subscript operator. For instance, `var.substring(5)` can be replaced with `var[5..-1]`.

Note that the String.substring(beginIndex,endIndex) method specifies a range of beginIndex..endIndex-1, while
Groovy's String subscript specifies an inclusive range. So, `"123456".substring(1, 5)` is equivalent to `"123456"[1..4]`.

Example of violations:

```
    myVar.substring(5)          // can use myVar[5..-1] instead
    myVar.substring(1, 5)       // can use myVar[1..4] instead
```



*Since CodeNarc 0.12 (formerly StringInstantiation Rule in the "basic" rule set)*

Checks for direct call to the `String` constructor that accepts a `String` literal.
In almost all cases, this is unnecessary. Use a `String` literal (e.g., "...") instead of calling the
corresponding `String` constructor (`new String("..")`) directly.

Here is an example of code that produces a violation:

```
    def s = new String('abc')
```



\<Since CodeNarc 1.6\>

Checks for methods that are missing an explicit `return` statement.

This rule skips `void` methods and `def` (dynamic return type) methods,
as well as methods whose last statement is a: - `throw` - `if` - `for` -
`while` - `do .. while` - `switch` - `try/catch`

Example of violations:

``` 
    boolean example() { true }          // violation
     
    protected int longerExample() {
        if (baseName == null) {
            return 0
        }
        99                              // violation
    }
```

Note: This rule is pretty much the opposite of the
[UnnecessaryReturnKeyword](./codenarc-rules-unnecessary.html#unnecessaryreturnkeyword-rule)
rule. Enabling both rules results in a paradox and may cause a rip in
the fabric of *space-time*. Or at least unwanted violations.

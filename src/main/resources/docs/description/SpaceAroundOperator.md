
*Since CodeNarc 0.18*

Check that there is at least one space (blank) or whitespace around each binary operator,
including: +, -, *, /, \*\*, \*\*, &&, ||, &, |, ?:, =, "as".

Do not check dot ('.') operator. Do not check unary operators (!, +, -, ++, --, ?.).
Do not check array ('[') operator.

| Property                    | Description            | Default Value    |
|-----------------------------|------------------------|------------------|
| ignoreParameterDefaultValueAssignments | If `true`, then do not check for whitespace around the '=' operator within method/constructor default parameter assignments. | `true` |

Known limitations:
  * Does not catch violations of certain ternary expressions and standalone elvis operator (?:) expressions.
  * Does not catch violations of missing space around the equals operator (=) for fields initialization if the field is annotated.

Examples of violations:

```
    def myMethod() {
        3+ 5-x*23/ 100              // violation
        list \*\*123                // violation
        other\*\* writer            // violation
        x=99                        // violation
        x&& y                       // violation
        x ||y                       // violation
        x &y                        // violation
        x| y                        // violation
        [1,2]as String              // violation
    }
```

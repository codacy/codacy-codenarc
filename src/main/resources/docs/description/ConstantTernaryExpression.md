Checks for ternary expressions with a constant value for the boolean
expression, such as `true`, `false`, `null`, or a literal constant
value. Examples of violations include:

``` 
    true ? x : y
    false ? x : y
    Boolean.TRUE ? x : y
    Boolean.FALSE ? x : y
    null ? x : y
    0 ? x : y
    99.7 ? x : y
    "" ? x : y
    "abc" ? x : y
    [:] ? x : y
    [a:123, b:456] ? x : y
    [a, b, c] ? x : y
```

The rule also checks for the same types of constant values for the
boolean expressions within the “short” ternary expressions, also known
as the “Elvis” operator, e.g.:

``` 
    true ?: y
    null ?: y
    99.7 ?: y
    "abc" ?: y
    [:] ?: y
    [a, b, c] ?: y
```

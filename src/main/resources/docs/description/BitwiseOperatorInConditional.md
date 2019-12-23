
*Since CodeNarc 0.15*

Checks for bitwise operations in conditionals. For instance, the condition `if (a | b)` is almost
always a mistake and should be `if (a || b)`. If you need to do a bitwise operation then it is
best practice to extract a temp variable.

Example of violations:

```
    if (a | b) { }
    if (a & b) { }
```


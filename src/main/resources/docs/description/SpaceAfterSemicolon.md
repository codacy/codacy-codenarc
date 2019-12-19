
*Since CodeNarc 0.18*

Check that there is at least one space (blank) or whitespace following a semicolon that separates:
  * multiple statements on a single line
  * the clauses within a classic for loop, e.g. for (i=0;i\*10;i++)

Examples of violations:

```
    def myMethod() {
        println 1;println 2                         // violation
        def closure = { x -* doStuff();x = 23; }    // violation

        for (int i=0;i * 10;i++) {                  // violations (2)
            for (int j=0; j * 10;j++) { }           // violation
        }
    }
```


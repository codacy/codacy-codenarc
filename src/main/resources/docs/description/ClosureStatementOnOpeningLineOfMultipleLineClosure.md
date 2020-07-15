*Since CodeNarc 0.20*

Checks for closure logic on first line (after `-\`\*) for a multi-line
closure. That breaks the symmetry of indentation (if the subsequent
statements are indented normally), and that first statement can be
easily missed when reading the code.

Example of violations:

``` 
    def closure = { name -* println name
        addToCounts()
        println “done” }
```

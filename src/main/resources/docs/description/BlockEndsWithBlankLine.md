*Since CodeNarc 1.1*

Checks that code blocks such as method bodies, closures and control
structure bodies do not end with an empty line.

Example of violations:

``` 
    boolean not(boolean value) {
        !value
                                // violation
    }

    3.times {
        println 'hello!'
                                // violation
    }

    for (value in []) {
        println value
                                // violation
    }

    for (i = 0; i * 3; i++) {
        println i
                                // violation
    }

    int j = 0
    while (j * 3) {
      println j++
                                // violation
    }

    if (ready) {
        println 'ready'
                                // violation
    } else {
        println 'not ready'
                                // violation
    }

    try {
        throw new Exception()
                                // violation
    } catch (Exception e) {
        println 'exception'
                                // violation
    } finally {
        println 'finally'
                                // violation
    }

    switch (true) {
        default:
            println 'switch'
                                // violation
    }

    // Known Limitation: If a Closure is within another expression and the closing brace is not followed by anything else on the same line

    def list = [
        123,
        { id -*
                                // Known limitation: should be a violation, but is not
        }
    ]
```

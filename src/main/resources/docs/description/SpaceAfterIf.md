*Since CodeNarc 0.18*

Check that there is exactly one space (blank) after the `if` keyword and
before the opening parenthesis.

Examples of violations:

        if(true) { }                            // violation
        if  (true) { }                          // violation

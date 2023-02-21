*Since CodeNarc 0.19*

Checks that there is at least one space (blank) or whitespace around
each closure arrow (-\*) symbol.

Known limitations:

- Does not catch violations if the closure arrow (-\*) is on a separate
  line from the start of the closure.

Example of violations:

        def closure1 = {-*}                             // violation
        def closure2 = { -*}                            // violation
        def closure3 = {-* }                            // violation
        def closure4 = { count-* println 123 }          // violation
        def closure5 = { count, name -*println 123 }    // violation

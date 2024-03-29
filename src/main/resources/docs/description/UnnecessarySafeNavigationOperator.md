*Since CodeNarc 0.22*

Check for the *safe navigation* operator (`?.`) applied to constants and
literals, or `this` or `super`, or constructor calls, all of which can
never be null.

Example of violations:

        def myMethod() {
            "abc"?.bytes            // violation
            [1,2]?.getSize()        // violation
            [abc:123]?.name         // violation
            [:]?.toString()         // violation
            123?.class              // violation
            123.45?.getClass()      // violation
            Boolean.FALSE?.class    // violation
            Boolean.TRUE?.class     // violation
            this?.class             // violation
            super?.getClass()       // violation
            new Long(100)?.class    // violation
        }

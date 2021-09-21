*Since CodeNarc 0.21*

Checks for multiple consecutive unary operators. These are confusing,
and are likely typos and bugs.

Example of violations:

        int z = ~~2             // violation
        boolean b = !!true      // violation
        boolean c = !!!false    // 2 violations
        int j = -~7             // violation
        int k = +~8             // violation

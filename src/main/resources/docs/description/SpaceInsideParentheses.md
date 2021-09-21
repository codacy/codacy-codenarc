*Since CodeNarc 2.1.0*

Check for whitespace after opening parentheses and before closing
parentheses

Example of violations:

        if( running) { }                        // violation
        if(running ) { }                        // violation
        if(      x < calculateLastIndex(        // violation
                'name') + 1    ) { }            // violation
                
        for(    String name: filterNames(       // violation
                names)   ) { }                  // violation

        println( 123 )                          // violations

        println (3 + ( 4 * 7 )+7) + (   5 * 1 ) // violations
        def v =  (y - 7 )*( x + (z - 3))        // violations
        def v2 =  (y - 7 ) *                    // violation
            (x + 
            ( z - 3)                            // violation
            )
        def v3 = calc(a) + calc( a + ( b - 1) * 2) + calc(7)  // violation
        def v4 = (a ) ? (17) : (19 )            // violation
        
        void doStuff( int n) { }                // violation

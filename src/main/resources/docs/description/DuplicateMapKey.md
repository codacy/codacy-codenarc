*Since CodeNarc 0.14*

A *Map* literal is created with duplicated key. The map entry will be
overwritten.

Example of violations:

        def var1 = [a:1, a:2, b:3]        //violation
        def var2 = [1:1, 1:2, 2:3]        //violation
        def var3 = ["a":1, "a":2, "b":3]  //violation

        // these are OK
        def var4 = [a:1, b:1, c:1]
        def var5 = [1:1, 2:1, 3:1]
        def var6 = ["a":1, "b":1, "c":1]

*Since CodeNarc 0.16*

In Java and Groovy, you can specify long literals with the L or l
character, for instance 55L or 24l. It is best practice to always use an
uppercase L and never a lowercase l. This is because 11l rendered in
some fonts may look like 111 instead of 11L.

Example of violations:

        def x = 1l
        def y = 55l

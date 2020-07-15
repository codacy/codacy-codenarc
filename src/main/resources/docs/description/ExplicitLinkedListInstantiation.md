*Since CodeNarc 0.11*

This rule checks for explicit calls to the no-argument constructor of
`LinkedList`. In Groovy, it is best to replace `new LinkedList()` with
`[] as Queue`, which creates the same object.

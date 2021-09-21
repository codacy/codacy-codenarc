*Since CodeNarc 0.11*

This rule checks for explicit calls to the no-argument constructor of
`TreeSet`. In Groovy, it is best to replace `new TreeSet()` with
`[] as SortedSet`, which creates the same object.

*Since CodeNarc 0.11*

This rule checks for explicit calls to the no-argument constructor of
`HashSet`. In Groovy, it is best to replace `new HashSet()` with `[] as
Set`, which creates the same object.

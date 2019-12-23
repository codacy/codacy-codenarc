
*Since CodeNarc 0.11*

This rule checks for explicit calls to the no-argument constructor of `Stack`. In Groovy, it is best to replace
`new Stack()` with `[] as Stack`, which creates the same object.

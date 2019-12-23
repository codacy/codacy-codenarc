
*Since CodeNarc 0.11*

This rule checks for explicit calls to the no-argument constructor of `HashMap`. In Groovy, it is best to replace
`new HashMap()` with `[:]`, which creates (mostly) the same object. `[:]` is technically a LinkedHashMap but it
is very rare that someone absolutely needs an instance of `HashMap` and not a subclass.


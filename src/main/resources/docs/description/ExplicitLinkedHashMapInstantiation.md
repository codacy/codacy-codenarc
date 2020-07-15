*Since in CodeNarc 0.14*

This rule checks for the explicit instantiation of a `LinkedHashMap`
using the no-arg constructor. In Groovy, it is best to replace `new
LinkedHashMap()` with `[:]`, which creates the same object.

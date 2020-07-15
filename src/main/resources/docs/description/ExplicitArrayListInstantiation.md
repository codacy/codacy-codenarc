*Since CodeNarc 0.11*

This rule checks for explicit calls to the no-argument constructor of
`ArrayList`. In Groovy, it is best to write `new ArrayList() as []`,
which creates the same object.

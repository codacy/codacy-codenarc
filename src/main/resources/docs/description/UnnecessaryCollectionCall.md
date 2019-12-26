
*Since CodeNarc 0.11*

Checks for useless calls to collections. For any collection `c`, calling `c.containsAll(c)`
should always be `true`, and `c.retainAll(c)` should have no effect.


*Since CodeNarc 0.11*

This rule detects calling JUnit style assertions like `assertEquals`, `assertTrue`,
`assertFalse`, `assertNull`, `assertNotNull`. Groovy 1.7 ships with a feature called the
"power assert", which is an assert statement with better error reporting. This is preferable to the
JUnit assertions.

*Since CodeNarc 0.12*

Checks for parameters to private methods (or constructors) that are not
referenced within the method body. Note that the `private` modifier is
not currently “respected” by Groovy code (i.e., Groovy can access
`private` members within other classes).

Known limitations: \* Does not recognize parameter references within an
inner class. See \[CodeNarc bug


*Since CodeNarc 0.11*

Returning null from a catch block often masks errors and requires the client to handle error codes. In some coding
styles this is discouraged. This rule ignores methods with `void` return type.


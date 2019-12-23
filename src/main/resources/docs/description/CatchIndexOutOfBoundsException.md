
*Since CodeNarc 0.12*

Checks for catching a `IndexOutOfBoundsException`. Catching `IndexOutOfBoundsException` should
be avoided in the first place by checking for a valid index before accessing an indexed element. Catching the
exception may mask underlying errors.

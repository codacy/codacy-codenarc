
*Since CodeNarc 0.12*

Checks for catching a `ArrayIndexOutOfBoundsException`. Catching `ArrayIndexOutOfBoundsException` should
be avoided in the first place by checking the array size before accessing an array element. Catching the
exception may mask underlying errors.



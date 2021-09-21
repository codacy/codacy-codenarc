*Since CodeNarc 0.11*

Checks for code that calls `notify()` rather than `notifyAll()`. Java
monitors are often used for multiple conditions. Calling `notify()` only
wakes up one thread, meaning that the awakened thread might not be the
one waiting for the condition that the caller just satisfied.

Also see
[**Java_Concurrency_in_Practice**](http://www.javaconcurrencyinpractice.com/),
Brian Goetz, p 303.

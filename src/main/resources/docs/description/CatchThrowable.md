Checks for catching a `Throwable`. In most cases that is much too broad,
and is also dangerous because it can catch exceptions such as
`ThreadDeath` and `OutOfMemoryError`.

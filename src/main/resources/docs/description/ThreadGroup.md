*Since CodeNarc 0.13*

Avoid using `ThreadGroup`; although it is intended to be used in a
threaded environment it contains methods that are not thread safe.

Here is an example of code that produces a violation:

        new ThreadGroup("...")
        new ThreadGroup(tg, "my thread group")
        Thread.currentThread().getThreadGroup()
        System.getSecurityManager().getThreadGroup()

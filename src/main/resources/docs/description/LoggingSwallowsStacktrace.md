*Since CodeNarc 0.12*

If you are logging an exception then the proper API is to call
error(Object, Throwable), which will log the message and the exception
stack trace. If you call error(Object) then the stacktrace may not be
logged.

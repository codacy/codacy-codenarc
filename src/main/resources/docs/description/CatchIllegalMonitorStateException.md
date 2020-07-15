*Since CodeNarc 0.11*

Dubious catching of IllegalMonitorStateException.
IllegalMonitorStateException is generally only thrown in case of a
design flaw in your code (calling wait or notify on an object you do not
hold a lock on).

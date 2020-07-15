*Since CodeNarc 0.13*

This rule detects double checked locking, where a ‘lock hint’ is tested
for null before initializing an object within a synchronized block.
Double checked locking does not guarantee correctness and is an
anti-pattern.

A full explanation of why double checked locking is broken in Java is
available on Wikipedia:
<http://en.wikipedia.org/wiki/Double-checked_locking>

Example of violations:

``` 
    if (object == null) {
        synchronized(this) {
            if (object == null) {
                // createObject() could be called twice depending
                // on the Thread Scheduler.
                object = createObject()
            }
        }
    }

    // there are several idioms to fix this problem.
    def result = object;
    if (result == null) {
        synchronized(this) {
            result = object;
            if (result == null)
                object = result = createObject()
        }
    }

    // and a better solution for a singleton:
    class myClass  {
        private static class ObjectHolder {
           public static Object object = createObject()
        }
        public static Object getObject() {
            return ObjectHolder.object;
        }
    }
```

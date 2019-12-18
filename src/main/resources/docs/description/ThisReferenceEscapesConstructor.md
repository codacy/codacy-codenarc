
*Since CodeNarc 0.19*

Reports constructors passing the 'this' reference to other methods.
This equals exposing a half-baked objects and can lead to race conditions during initialization.
For reference, see [Java Concurrency in Practice](http://www.slideshare.net/alexmiller/java-concurrency-gotchas-3666977/38) by Alex Miller
and [Java theory and practice: Safe construction techniques](http://www.ibm.com/developerworks/java/library/j-jtp0618/index.html) by Brian Goetz.

Example of violations:

```
    class EventListener {
        EventListener(EventPublisher publisher) {
            publisher.register(this)
            new WorkThread(publisher, this).start()
            new AnotherWorkThread(listener: this)
        }
    }
```



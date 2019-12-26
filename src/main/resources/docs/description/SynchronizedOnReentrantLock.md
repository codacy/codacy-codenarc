
*Since CodeNarc 0.13*

Synchronizing on a ReentrantLock field is almost never the intended usage. A ReentrantLock should be obtained using
the lock() method and released in a finally block using the unlock() method.

This rule take from Alex Miller's [Java Concurrency in Practice](http://www.slideshare.net/alexmiller/java-concurrency-gotchas-3666977) slides.

Here is the proper usage of ReentrantLock:

```
    import java.util.concurrent.locks.ReentrantLock;
    final lock = new ReentrantLock();
    def method()  {
       //Trying to enter the critical section
       lock.lock(); // will wait until this thread gets the lock
       try {
          // critical section
       } finally {
          //releasing the lock so that other threads can get notifies
          lock.unlock();
       }
    }
```

Example of violations:

```
    class MyClass {

        final ReentrantLock lock = new ReentrantLock()

        def method() {
            // violation
            synchronized(lock) { }
        }
    }

    class MyClass {

        final ReentrantLock lock = new ReentrantLock()

        class MyInnerClass {
            def method() {
                synchronized(lock) { }
            }
        }
    }

    class MyClass {
        // implicit typing
        final def lock = new ReentrantLock()

        def method() {
            // violation
            synchronized(lock) { }
        }
    }

    class MyClass {
        // implicit typing
        final def lock = new Object[0] // correct idiom

        def method() {
            return new Runnable() {
                final def lock = new ReentrantLock() // shadows parent from inner class
                public void run() {
                    // violation
                    synchronized(lock) { }
                }
            }
        }
    }

    class MyClass {
        // implicit typing
        final def lock = new Object[0] // correct idiom

        class MyInnerClass {

            final def lock = new ReentrantLock() // shadows parent from inner class
            def method() {
                // violation
                synchronized(lock) { }
            }
        }
    }
```


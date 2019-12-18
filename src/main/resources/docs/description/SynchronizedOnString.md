
*Since CodeNarc 0.13*

Synchronization on a String field can lead to deadlock. Constant Strings are interned and shared across all other
classes loaded by the JVM. Thus, this could is locking on something that other code might also be locking. This could
result in very strange and hard to diagnose blocking and deadlock behavior.

See [JETTY-352](http://www.javalobby.org/java/forums/t96352.html) and <http://jira.codehaus.org/browse/JETTY-352>.

Examples:

```
    class MyClass {

        final String stringLock = "stringLock"

        def method() {
            // violation
            synchronized(stringLock) { }
        }
    }

    class MyClass {

        final String stringLock = "stringLock"

        class MyInnerClass {
            def method() {
                synchronized(stringLock) { }
            }
        }
    }

    class MyClass {
        // implicit typing
        final def stringLock = "stringLock"

        def method() {
            // violation
            synchronized(stringLock) { }
        }
    }

    class MyClass {
        // implicit typing
        final def lock = new Object[0] // correct idiom

        def method() {
            return new Runnable() {
                final def lock = "" // shadows parent from inner class
                public void run() {
                    // violation
                    synchronized(stringLock) { }
                }
            }
        }
    }

    class MyClass {
        // implicit typing
        final def lock = new Object[0] // correct idiom

        class MyInnerClass {

            final def lock = "" // shadows parent from inner class
            def method() {
                // violation
                synchronized(stringLock) { }
            }
        }
    }
```


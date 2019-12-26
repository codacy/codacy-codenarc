
*Since CodeNarc 0.13*

Catches Serializable classes that define a synchronized readObject method. By definition, an object created by
deserialization is only reachable by one thread, and thus there is no need for readObject() to be synchronized. If
the readObject() method itself is causing the object to become visible to another thread, that is an example of very
dubious coding style.

Examples:

```
    class MyClass implements Serializable {

        private synchronized void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException {
            // violation, no need to synchronized
        }
    }

    class MyClass implements Serializable {

        private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException {
            synchronized(lock) {
                // violation, no need to synchronized
            }
        }
    }

    // OK, class not Serializable
    class MyClass {

        private synchronized void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException { }
    }

    // OK, class not Serializable
    class MyClass {

        private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException {
            synchronized(lock) { }
        }
    }

    class MyClass implements Serializable {

        private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException {
            // OK, this block is more than just a simple sync statement
            synchronized(lock) { }
            doSomething()
        }
    }
```

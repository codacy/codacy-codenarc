
*Since CodeNarc 0.13*

Calls to `Object.wait()` must be within a `while` loop. This ensures that the awaited condition
has not already been satisfied by another thread before the `wait()` is invoked. It also ensures that
the proper thread was resumed and guards against incorrect notification. See [1] and [3].

As a more modern and flexible alternative, consider using the Java *concurrency utilities* instead of
`wait()` and `notify()`. See discussion in *Effective Java* [2].

Example of violation:

```
    class MyClass {
        private data

        void processData()
            synchronized(data) {
                if (!data.isReady()) {
                    data.wait()
                }
                data.calculateStatistics()
            }
        }
    }
```

Example of correct usage:

```
    class MyClass {
        private data

        void processData()
            synchronized(data) {
                while (!data.isReady()) {
                    data.wait()
                }
                data.calculateStatistics()
            }
        }
    }
```

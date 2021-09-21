This rule reports uses of the `synchronized` blocks where the
synchronization reference is ‘this’. Doing this effectively makes your
synchronization policy public and modifiable by other objects. To avoid
possibilities of deadlock, it is better to synchronize on internal
objects.

Here is an example of code that produces a violation:

        def method3() {
            synchronized(this) {
                // do stuff ...
            }
        }

*Since CodeNarc 0.13*

Volatile array fields are unsafe because the contents of the array are
not treated as volatile. Changing the entire array reference is visible
to other threads, but changing an array element is not.

This rule take from Alex Miller’s *Java Concurrency in Practice* slides,
available at
<http://www.slideshare.net/alexmiller/java-concurrency-gotchas-3666977>

Example of violations:

        class MyClass {
            private volatile Object[] field1 = value()
            volatile field2 = value as Object[]
            volatile field3 = (Object[])foo
        }

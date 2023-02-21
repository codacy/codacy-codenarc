*Since CodeNarc 0.14*

Using `Class.forName(...)` is a common way to add dynamic behavior to a
system. However, using this method can cause resource leaks because the
classes can be pinned in memory for long periods of time. If you’re
forced to do dynamic class loading then use ClassLoader.loadClass
instead. All variations of the `Class.forName(...)` method suffer from
the same problem.

For more information see these links:

- <http://blog.bjhargrave.com/2007/09/classforname-caches-defined-class-in.html>

- <http://www.osgi.org/blog/2011/05/what-you-should-know-about-class.html>

Example of violations:

        Class.forName('SomeClassName')
        Class.forName(aClassName, true, aClassLoader)

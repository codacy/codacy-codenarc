*Since CodeNarc 1.0*

Checks for explicit calls to setter methods which can, for the most
part, be replaced by assignment to property. A setter is defined as a
method call that matches set\[A-Z\] but not set\[A-Z\]\[A-Z\] such as
setURL(). Setters take one method argument. Setter calls within an
expression are ignored. Calls to static setter methods within the same
class are ignored.

These bits of code produce violations:

      x.setProperty(1)
      x.setProperty(this.getA())
      x.setProperty([])

These bits of code do not:

      x.set(1)                              // Nothing after "set"
      x.setup(2)                            // The letter after "set" must be capitalized
      x.setURL('')                          // But setters with multiple capital letters after "set" are ignored
      x.setSomething('arg1', 'arg2')        // Setter must have exactly one argument
      if (!file.setExecutable(true)) { }    // Set method called within expression
      def count = x.setCount(92)            // Set method called within expression

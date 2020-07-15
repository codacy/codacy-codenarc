*Since CodeNarc 0.11*

Checks for synchronization on `getClass()` rather than class literal.
This instance method synchronizes on `this.getClass()`. If this class is
subclassed, subclasses will synchronize on the class object for the
subclass, which isn’t likely what was intended.

*Since CodeNarc 0.24*

Checks for any interface that has an identical name to its
super-interface, other than the package. This can be very confusing.

Example of violations:

        interface MyInterface extends other.MyInterface { }     // violation

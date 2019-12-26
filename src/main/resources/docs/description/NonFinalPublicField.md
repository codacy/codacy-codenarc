
*Since CodeNarc 0.14*

Finds code that violates secure coding principles for mobile code by declaring a member variable public but not final.

All public member variables in an Applet and in classes used by an Applet should be declared final to prevent an attacker
from manipulating or gaining unauthorized access to the internal state of the Applet.

References:
  * Standards Mapping - Common Weakness Enumeration - (CWE) CWE ID 493
  * G. McGraw Securing Java. Chapter 7: Java Security Guidelines

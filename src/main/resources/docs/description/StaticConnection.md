
*Since CodeNarc 0.14*

Creates violations when a `java.sql.Connection` object is used as a `static` field. Database connections
stored in `static` fields will be shared between threads, which is unsafe and can lead to race conditions.

A transactional resource object such as database connection can only be associated with one transaction at a time.
For this reason, a connection should not be shared between threads and should not be stored in a static field.
See Section 4.2.3 of the *J2EE Specification* for more details.

References:
  * Standards Mapping - Security Technical Implementation Guide Version 3 - (STIG 3) APP3630.1 CAT II
  * Standards Mapping - Common Weakness Enumeration - (CWE) CWE ID 362, CWE ID 567
  * Standards Mapping - SANS Top 25 2009 - (SANS 2009) Insecure Interaction - CWE ID 362
  * Standards Mapping - SANS Top 25 2010 - (SANS 2010) Insecure Interaction - CWE ID 362
  * Java 2 Platform Enterprise Edition Specification, v1.4 Sun Microsystems

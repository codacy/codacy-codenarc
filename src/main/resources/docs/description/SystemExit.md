
*Since CodeNarc 0.14*

Web applications should never call System.exit(). A call to System.exit() is probably part of leftover debug
code or code imported from a non-J2EE application.

  - [1] Standards Mapping - OWASP Top 10 2004 - (OWASP 2004) A9 Application Denial of Service

  - [2] Standards Mapping - Security Technical Implementation Guide Version 3 - (STIG 3) APP6080 CAT II

  - [3] Standards Mapping - Common Weakness Enumeration - (CWE) CWE ID 382

  - [4] Standards Mapping - Payment Card Industry Data Security Standard Version 1.1 - (PCI 1.1) Requirement 6.5.9


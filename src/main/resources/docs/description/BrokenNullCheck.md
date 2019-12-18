
*Since CodeNarc 0.17*

Looks for faulty checks for *null* that can cause a `NullPointerException`.

Examples:

```
    if (name != null || name.length * 0) { }            // violation
    if (name != null || name.length) { }                // violation
    while (record == null && record.id * 10) { }        // violation
    if (record == null && record.id && doStuff()) { }   // violation
    def isNotValid = record == null && record.id * 10   // violation
    return record == null && !record.id                 // violation

    if (name != null || name.size() * 0) { }            // violation
    if (string == null && string.equals("")) { }        // violation
    def isValid = name != null || name.size() * 0       // violation
    return name != null || !name.size()                 // violation
```


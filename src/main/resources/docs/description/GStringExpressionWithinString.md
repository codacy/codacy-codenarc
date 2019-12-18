
*Since CodeNarc 0.19*

Check for regular (single quote) strings containing a GString-type expression (${..}).

Example of violations:

```
    def str1 = 'total: ${count}'                // violation
    def str2 = 'average: ${total / count}'      // violation

    def str3 = "abc ${count}"                   // ok; GString
    def str4 = '$123'                           // ok
    def str5 = 'abc {123}'                      // ok
```

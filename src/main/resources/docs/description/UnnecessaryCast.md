*Since CodeNarc 0.21*

Checks for unnecessary cast operations.

Example of violations:

``` 
    int count = (int)123                    // violation
    def longValue = (long)123456L           // violation
    def bigDecimal = (BigDecimal)1234.56    // violation
    String name = (String) "Joe"            // violation
    def list = (List)[1, 2, 3]              // violation
    def map = (Map)[a:1]                    // violation
```

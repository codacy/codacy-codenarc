*Since CodeNarc 0.11*

A GString should not be used as a map key since its *hashcode* is not
guaranteed to be stable. Consider calling `key.toString()`.

Here is an example of code that produces a violation:

``` 
    Map map = ["${someRef}" : 'invalid' ]       // violation
```

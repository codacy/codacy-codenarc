*Since CodeNarc 0.15*

This rule finds usages of a Windows file separator within the
constructor call of a File object. It is better to use the Unix file
separator or use the File.separator constant.

Example of violations:

``` 
   new File('.\\foo\\')
   new File('c:\\dir')
   new File('../foo\\')
```

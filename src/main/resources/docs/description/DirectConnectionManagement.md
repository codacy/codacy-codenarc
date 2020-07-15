*Since CodeNarc 0.14*

The J2EE standard requires that applications use the container’s
resource management facilities to obtain connections to resources. Every
major web application container provides pooled database connection
management as part of its resource management framework. Duplicating
this functionality in an application is difficult and error prone, which
is part of the reason it is forbidden under the J2EE standard.

For more information see:
<https://vulncat.fortify.com/en/detail?id=desc.semantic.java.j2ee_badpractices_getconnection>.

Example of violations:

``` 
    DriverManager.getConnection()
    java.sql.DriverManager.getConnection()
```

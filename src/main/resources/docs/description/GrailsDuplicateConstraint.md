*Since CodeNarc 0.18*

Check for duplicate name in a Grails domain class constraints. Duplicate
names/entries are legal, but can be confusing and error-prone.

NOTE: This rule does not check that the values of the entries are
duplicated, only that there are two entries with the same name.

Example of violations:

``` 
    class Person {
        String firstName
        String lastName

        static constraints = {
            firstName nullable:true
            lastName nullable:true, maxSize:30
            firstName nullable:false                // violation
        }
    }
```

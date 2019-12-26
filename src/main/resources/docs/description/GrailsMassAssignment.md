
*Since CodeNarc 0.21*

Untrusted input should not be allowed to set arbitrary object fields without restriction.

Example of violations:

```
   // Person would be a grails domain object
   def person = new Person(params)
   person.save()

   // or using .properties
   def person = Person.get(1)
   person.properties = params
   person.save()
```

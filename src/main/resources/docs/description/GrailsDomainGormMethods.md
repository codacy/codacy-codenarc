\<Since CodeNarc 1.6\>

Database operation should be performed by Data Services instead of
calling GORM static and instance methods.

Using the GORM static and instance methods may lead to spreading the
persistence logic across the whole application instead of concentrating
it into services. It makes difficult to find all the code working with
the database in case of upgrades to the newer versions of Grails which
require all persistence code running inside transactions.

Data Services are available since Grails 3.3 and GORM 6.1.

NOTE: This is a [CodeNarc Enhanced Classpath
Rule](./codenarc-enhanced-classpath-rules.html). It requires
**CodeNarc** to have the application classes being analyzed, as well as
any referenced classes, on the classpath.

Example of violations:

        class Person {
            String firstName
            String lastName
        }

        class PersonService {
            
            Person createPerson(String firstName, String lastName) {
                Person person = new Person(firstName: firstName, lastName: lastName)
                return person.save()
            }
        
        }

Example of valid configuration:

        class Person {
            String firstName
            String lastName
        }

        @Service(Person)
        class PersonDataService {
            Person save(Person person)
        }

        class PersonService {

            PersonDataService personDataService
            
            Person createPerson(String firstName, String lastName) {
                Person person = new Person(firstName: firstName, lastName: lastName)
                return personDataService.save(person)
            }
        
        }

See \[GORM Data
Services\](https://gorm.grails.org/latest/hibernate/manual/index.htm

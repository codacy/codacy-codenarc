
*Since CodeNarc 0.21*

Checks for unnecessary calls to `toString()`. This includes:

  * Calls to `toString()` on a String literal or expression

  * Calls to `toString()` for the value assigned to a `String` field or variable (if *checkAssignments* is `true`).

| Property                    | Description            | Default Value    |
|-----------------------------|------------------------|------------------|
| checkAssignments | If `true`, then check for calls to `toString()` for the value assigned to a `String` field or variable. | `true` |

Example of violations:

```
    def name = "Joe".toString()                             // violation - string literal
    def groupId = ((String)row.get('GroupID')).toString()   // violation - string expression

    class MyClass {
        String name = nameNode.toString()           // violation - field
        String code = account.getCode().toString()  // violation - field

        void run() {
            String name = nameNode.toString()       // violation - variable
            String id = account.id.toString()       // violation - variable
        }
    }
```


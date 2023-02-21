*Since CodeNarc 0.21*

Checks for unnecessary calls to `toString()`. This includes:

- Calls to `toString()` on a String literal or expression

- Calls to `toString()` for the value assigned to a `String` field or
  variable (if *checkAssignments* is `true`).

<table>
<colgroup>
<col style="width: 40%" />
<col style="width: 33%" />
<col style="width: 25%" />
</colgroup>
<thead>
<tr class="header">
<th>Property</th>
<th>Description</th>
<th>Default Value</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td>checkAssignments</td>
<td>If <code>true</code>, then check for calls to
<code>toString()</code> for the value assigned to a <code>String</code>
field or variable.</td>
<td><code>true</code></td>
</tr>
</tbody>
</table>

Example of violations:

        def name = "Joe".toString()                             // violation - string literal
        def groupId = ((String)row.get('GroupID')).toString()   // violation - string expression

        class MyClass {
            String name = nameNode.toString()           // violation - field
            String code = account.getCode().toString()  // violation - field

            def name = "Joe" + new Date().toString()    // violation - adding object to String

            void run() {
                String name = nameNode.toString()       // violation - variable
                String id = account.id.toString()       // violation - variable

                def string = "processing ${123L.toString()} or ${new Date().toString()}"    // 2 violations - GString value
            }
        }

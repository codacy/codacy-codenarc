*Since CodeNarc 0.18*

Check that there is at least one space (blank) or whitespace after each
opening brace (“{”) for method/class/interface declarations, closure
expressions and block statements.

<table>
<colgroup>
<col style="width: 40%" />
<col style="width: 33%" />
<col style="width: 25%" />
</colgroup>
<thead>
<tr>
<th>Property</th>
<th>Description</th>
<th>Default Value</th>
</tr>
</thead>
<tbody>
<tr>
<td>checkClosureMapEntryValue</td>
<td>If <code>false</code>, then do not check for whitespace after
opening braces for closure expressions that are literal Map values,
e.g. <code>[abc:\{doStuff()\}]</code>.</td>
<td><code>true</code></td>
</tr>
<tr>
<td>ignoreEmptyBlock</td>
<td>If <code>true</code>, then allow for <code>\{\}</code> in code</td>
<td><code>false</code></td>
</tr>
</tbody>
</table>

Examples of violations:

        class MyClass{int count }                   // violation

        interface MyInterface {static final OK = 1 }// violation

        enum MyEnum {OK, BAD }                      // violation

        def myMethod() {int count }                 // violation

        if (ready) {println 9 }                     // violation

        if (ready) {
        } else {println 99}                         // violation

        for (int i=0; i*10; i++) {println i }       // violation

        for (String name in names) {println name }  // violation

        for (String name: names) {println name }    // violation

        while (ready) {println time }               // violation

        try {doStuff()                              // violation
        } catch(Exception e) {x=77 }                // violation
        } finally {println 'error' }                // violation

        list.each {name -* }                        // violation

        shouldFail(Exception) {doStuff() }          // violation

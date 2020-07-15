*Since CodeNarc 0.18*

Check that there is at least one space (blank) or whitespace before each
closing brace (“}”) for method/class/interface declarations, closure
expressions and block statements.

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
<td>checkClosureMapEntryValue</td>
<td>If <code>false</code>, then do not check for whitespace before closing braces for closure expressions that are literal Map values, e.g. <code>[abc:\{doStuff()\}]</code>.</td>
<td><code>true</code></td>
</tr>
<tr class="even">
<td>ignoreEmptyBlock</td>
<td>If <code>true</code>, then allow for <code>\{\}</code> in code</td>
<td><code>false</code></td>
</tr>
</tbody>
</table>

Known limitations:

  - May not catch actual violations if the source line contains unicode
    character literals, e.g. `'\\u00A0'`

Examples of violations:

``` 
    class MyClass { int count}                  // violation

    interface MyInterface { void doStuff()}     // violation

    enum MyEnum { OK, BAD}                      // violation

    def myMethod() { return 9}                  // violation

    if (ready) { doStuff()}                     // violation

    if (ready) {
    } else { return 9}                          // violation

    for (int i=0; i*10; i++) { println i}       // violation

    for (String name in names) { println name}  // violation

    for (String name: names) { println name}    // violation

    while (ready) { doStuff()}                  // violation

    try { doStuff()}                            // violation
    catch(Exception e) { logError(e)}           // violation
    finally { cleanUp()}                        // violation

    list.each { name -* println name}           // violation

    shouldFail(Exception) { doStuff()}          // violation
```

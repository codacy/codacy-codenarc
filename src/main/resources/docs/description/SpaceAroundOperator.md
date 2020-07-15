*Since CodeNarc 0.18*

Check that there is at least one space (blank) or whitespace around each
binary operator, including: +, -, \*, /, \*\*, \*\*, &&, ||, &, |, ?:,
=, “as”.

Do not check dot (‘.’) operator. Do not check unary operators (\!, +, -,
++, –, ?.). Do not check array (‘\[’) operator.

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
<td>ignoreParameterDefaultValueAssignments</td>
<td>If <code>true</code>, then do not check for whitespace around the ‘=’ operator within method/constructor default parameter assignments.</td>
<td><code>true</code></td>
</tr>
</tbody>
</table>

Known limitations: \* Does not catch violations of certain ternary
expressions and standalone elvis operator (?:) expressions. \* Does not
catch violations of missing space around the equals operator (=) for
fields initialization if the field is annotated.

Examples of violations:

``` 
    def myMethod() {
        3+ 5-x*23/ 100              // violation
        list \*\*123                // violation
        other\*\* writer            // violation
        x=99                        // violation
        x&& y                       // violation
        x ||y                       // violation
        x &y                        // violation
        x| y                        // violation
        [1,2]as String              // violation
    }
```

*Since CodeNarc 0.14*

If the last parameter of a method call is an inline closure then it can
be declared outside the method call parentheses.

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
<td>ignoreCallsToMethodNames</td>
<td>Specifies one or more (comma-separated) method names; method calls
on the named methods are ignored (i.e., do not cause a rule violation).
The names may optionally contain wildcards (*,?).</td>
<td><code>null</code></td>
</tr>
</tbody>
</table>

Example of violations:

        // creates violation: poor Groovy style
        [1,2,3].each({ println it })

        // no violation
        [1,2,3].each { println it }

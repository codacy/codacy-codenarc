*Since CodeNarc 1.1*

Checks that variable types are explicitly specified in declarations (and
not using `def`).

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
<td>ignoreVariableNames</td>
<td>Specifies one or more (comma-separated) variable names that should be ignored (i.e., that should not cause a rule violation). The names may optionally contain wildcards (*,?).</td>
<td><code>null</code></td>
</tr>
</tbody>
</table>

Example of violations:

        class MyClass {
            void doStuff() {
                final NAME = "joe"          // violation
                def count = 0, max = 99     // violation
                def defaultName             // violation
            }
        }

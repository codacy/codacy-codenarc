*Since CodeNarc 1.1*

Checks that field types are explicitly specified (and not using `def`).

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
<td>ignoreFieldNames</td>
<td>Specifies one or more (comma-separated) field names that should be ignored (i.e., that should not cause a rule violation). The names may optionally contain wildcards (*,?).</td>
<td><code>null</code></td>
</tr>
</tbody>
</table>

Example of violations:

        class MyClass {
            public static final NAME = "joe"        // violation
            private static count = 0                // violation

            private def name = NAME                 // violation
            protected final date = new Date()       // violation

            def defaultName                         // violation
            def maxSoFar = -1L                      // violation
        }

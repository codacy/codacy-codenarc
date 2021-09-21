*Since CodeNarc 1.1*

Checks that method return types are not dynamic, that is they are
explicitly stated and different than def.

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
<td>ignoreMethodNames</td>
<td>Specifies one or more (comma-separated) method names that should be ignored (i.e., that should not cause a rule violation). The names may optionally contain wildcards (*,?).</td>
<td><code>null</code></td>
</tr>
</tbody>
</table>

Example of violations:

        def methodWithDynamicReturnType() {    // violation
        }

        private methodWithoutReturnType() {    // violation
        }

        Object objectReturningMethod() {       // OK
        }

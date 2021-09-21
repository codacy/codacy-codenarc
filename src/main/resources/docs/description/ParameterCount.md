*Since CodeNarc 0.23*

Checks if the number of parameters in method/constructor exceeds the
number of parameters specified by the maxParameters property.

Example of violations:

        void someMethod(int arg1, int arg2, int arg3, int arg4, int arg5, int arg6) { // violation
        }

        @Override
        void someMethod(int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7) { // no violation if ignoreOverriddenMethods == true
        }

        class SampleClass {
            SampleClass(int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7) { // violation
            }
        }

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
<td>maxParameters</td>
<td>The maximum number of parameters in method/constructor</td>
<td>5</td>
</tr>
<tr class="even">
<td>ignoreOverriddenMethods</td>
<td>Ignore number of parameters for methods with <span class="citation" data-cites="Override">@Override</span> annotation</td>
<td><code>true</code></td>
</tr>
</tbody>
</table>

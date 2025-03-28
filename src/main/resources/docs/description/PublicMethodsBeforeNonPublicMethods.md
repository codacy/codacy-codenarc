*Since CodeNarc 1.2*

Enforce that all public methods are above protected and private methods.

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
<td>ignoreMethodNames</td>
<td>Specifies one or more (comma-separated) method names that should be
ignored. The named methods will not cause a rule violation, and will not
contribute to causing a violation on another method. The names may
optionally contain wildcards (*,?).</td>
<td><code>null</code></td>
</tr>
</tbody>
</table>

Example of violations:

        class MyClass {
            public static int staticMethod1() { }

            protected String method1() { }

            static final String staticMethod2() { }     // violation
            public String method2() { }                 // violation

            private int method3(int id) { }
        }

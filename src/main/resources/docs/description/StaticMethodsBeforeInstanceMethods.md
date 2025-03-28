*Since CodeNarc 1.2*

Enforce that all static methods within each visibility level (public,
protected, private) are above all instance methods within that same
visibility level. In other words, public static methods must be above
public instance methods, protected static methods must be above
protected instance methods and private static methods must be above
private instance methods.

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
                // Public
                public static int staticMethod1() { }
                public String method1() { }
                int method2() { }
                static final String staticMethod2(int id) { }       // violation

                // Protected
                protected String method3() { }
                protected static staticMethod3() { }                // violation

                // Private
                private int method4() { }
                private int method5() { }
                private static staticMethod4() { }                  // violation
                private String method5() { }
            }
        }

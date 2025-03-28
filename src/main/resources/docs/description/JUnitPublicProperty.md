*Since CodeNarc 0.21*

Checks for public properties defined on JUnit test classes. There is
typically no need to expose a public property (with public *getter* and
*setter* methods) on a test class.

This rule sets the default value of the *applyToClassNames* property to
only match class names ending in ‘Spec’, ‘Test’, ‘Tests’ or ‘TestCase’.

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
<td>ignorePropertyNames</td>
<td>Specifies one or more (comma-separated) property names that should
be ignored (i.e., that should not cause a rule violation). The names may
optionally contain wildcards (*,?).</td>
<td><code>null</code></td>
</tr>
</tbody>
</table>

Example of violations:

        import org.junit.Test
        class MyTestCase {
            static String id    // violation
            def helper          // violation
            String name         // violation

            @Test
            void testMe() { }
        }

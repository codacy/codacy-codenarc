*Since CodeNarc 3.3.0*

Spock treats all expressions on the first level of a then or expect
block as an implicit assertion. However, everything inside
if/for/switch/… blocks is not an implicit assert, just a useless
comparison (unless wrapped by a `with` or `verifyAll`).

This rule finds such expressions, where an explicit call to `assert`
would be required. Please note that the rule might produce false
positives, as it relies on method names to determine whether an
expression has a boolean type or not.

Example of violations:

        public class MySpec extends spock.lang.Specification {
            def "test passes - does not behave as expected"() {
                expect:
                if (true) {
                    true == false // violation - is inside an if block, and therefore not treated as an implicit assertion by spock
                }
            }

            def "test fails - behaves as expected"() {
                expect:
                if (true) {
                    with(new Object()) {
                        true == false // no violation - expressions in with are treated as implicit assertions by spock
                    }
                }
            }
        }

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
<td>specificationClassNames</td>
<td>Specifies one or more (comma-separated) class names that should be
treated as Spock Specification classes. The class names may optionally
contain wildcards (<em>,?), e.g. “</em>Spec”.</td>
<td><code>null</code></td>
</tr>
<tr>
<td>specificationSuperclassNames</td>
<td>Specifies one or more (comma-separated) class names that should be
treated as Spock Specification superclasses. In other words, a class
that extends a matching class name is considered a Spock Specification .
The class names may optionally contain wildcards (<em>,?),
e.g. “</em>Spec”.</td>
<td>“*Specification”</td>
</tr>
</tbody>
</table>

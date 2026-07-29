*Since CodeNarc 0.14*

If Spock’s `@IgnoreRest` annotation appears on any method, all
non-annotated test methods are not executed. This behaviour is almost
always unintended. It’s fine to use @IgnoreRest locally during
development, but when committing code, it should be removed.

The *specificationClassNames* and *specificationSuperclassNames*
properties determine which classes are considered Spock *Specification*
classes.

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
<td>specificationClassNames</td>
<td>Specifies one or more (comma-separated) class names that should be
treated as Spock Specification classes. The class names may optionally
contain wildcards (<em>,?), e.g. “</em>Spec”.</td>
<td><code>null</code></td>
</tr>
<tr class="even">
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

Example of violations:

        public class MySpec extends spock.lang.Specification {
            @spock.lang.IgnoreRest
            def "my first feature"() {
                expect: false
            }

            def "my second feature"() {
                given: def a = 2

                when: a *= 2

                then: a == 4
            }
        }

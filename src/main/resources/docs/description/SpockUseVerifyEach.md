*Since CodeNarc 3.7.0*

Checks for `.every`, `.each`, `.eachWithIndex`, or `.forEach` calls
containing assertions in Spock specifications that should use Spock
2.4’s `verifyEach` instead. Using `verifyEach` provides better per-item
failure diagnostics by collecting all failures individually rather than
failing fast on the first item.

In `then:` and `expect:` blocks, the rule detects: -
`.every { booleanExpr }` — implicit boolean assertion -
`.each`/`.eachWithIndex`/`.forEach` with closures containing `assert`
statements or boolean expressions

When `checkAllBlocks` is enabled (the default), the rule also detects
`.each`/`.eachWithIndex`/`.forEach` with explicit `assert` statements in
other blocks (e.g., `given:`, `when:`) and in helper methods.

Example of violations:

        class MySpec extends spock.lang.Specification {
            def "test"() {
                given:
                def list = [1, 2, 3]

                expect:
                list.every { it > 0 }                   // violation - use verifyEach(list) { it > 0 }
                list.each { assert it > 0 }              // violation - use verifyEach(list) { it > 0 }
                list.eachWithIndex { item, i ->
                    assert item > 0                      // violation - use verifyEach(list) { item, i -> item > 0 }
                }
                list.forEach { assert it > 0 }           // violation - use verifyEach(list) { it > 0 }
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
<td>checkAllBlocks</td>
<td>If <code>true</code>, also checks blocks other than
<code>then:</code> and <code>expect:</code> (and helper methods) for
<code>.each</code>/<code>.eachWithIndex</code>/<code>.forEach</code>
with explicit <code>assert</code> statements.</td>
<td><code>true</code></td>
</tr>
<tr class="even">
<td>specificationClassNames</td>
<td>Specifies one or more (comma-separated) class names that should be
treated as Spock Specification classes. The class names may optionally
contain wildcards (<em>,?), e.g. “</em>Spec”.</td>
<td><code>null</code></td>
</tr>
<tr class="odd">
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

**NOTE:** This rule requires Spock 2.4+ which introduces the
`verifyEach` method. If you are using an older version of Spock, disable
this rule.

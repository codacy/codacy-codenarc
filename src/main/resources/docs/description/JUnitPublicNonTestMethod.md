Rule that checks if a JUnit test class contains public methods other
than standard test methods, JUnit framework methods or methods with
JUnit annotations.

The following public methods are ignored by this rule: \* Zero-argument
methods with names starting with “test” \* The `setUp()` and
`tearDown()` methods \* Methods annotated with `@Test` \* Methods
annotated with `@Before`, `@BeforeAll`, `@BeforeClass` and `@BeforeEach`
\* Methods annotated with `@After`, `@AfterAll`, `@AfterClass` and
`@AfterEach` \* Methods annotated with `@Disabled` and `@Ignore` \*
Methods annotated with `@Override`

Public, non-test methods on a test class violate conventional usage of
test classes, and they typically break encapsulation unnecessarily.

Public, non-test methods may also hide unintentional *‘Lost Tests’*. For
instance, the test method declaration may (unintentionally) include
methods parameters, and thus be ignored by JUnit. Or the method may
(unintentionally) not follow the “test..” naming convention and not have
the @Test annotation, and thus be ignored by JUnit.

This rule sets the default value of the *applyToClassNames* property to
only match class names ending in ‘Spec’, ‘Test’, ‘Tests’ or ‘TestCase’.

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
<td>ignoreMethodsWithAnnotations</td>
<td>Specifies one or more (comma-separated) annotation names. Methods annotated with the annotations are ignored by this rule.</td>
<td>After,AfterAll,AfterClass, AfterEach,Before,BeforeAll, BeforeClass,BeforeEach, Disabled,Ignore, Override,Test</td>
</tr>
</tbody>
</table>

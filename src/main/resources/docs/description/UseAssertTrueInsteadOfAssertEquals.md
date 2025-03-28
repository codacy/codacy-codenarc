*Since CodeNarc 0.11*

This rule detects JUnit calling `assertEquals` where the first parameter
is a boolean. These assertions should be made by more specific methods,
like `assertTrue` or `assertFalse`.

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
<td>checkAssertStatements</td>
<td>If <code>true</code>, then also check assert statements,
e.g. <code>assert x == true</code>.</td>
<td><code>false</code></td>
</tr>
</tbody>
</table>

All of the following examples can be simplified to assertTrue or remove
the true literal:

        assertEquals(true, foo())
        assertEquals("message", true, foo())
        assertEquals(foo(), true)
        assertEquals("message", foo(), true)
        assertEquals(false, foo())
        assertEquals("message", false, foo())
        assertEquals(foo(), false)
        assertEquals("message", foo(), false)

        assert true == foo()                    // violation only if checkAssertStatements == true
        assert foo() == true : "message"        // violation only if checkAssertStatements == true
        assert false == foo()                   // violation only if checkAssertStatements == true
        assert foo() == false : "message"       // violation only if checkAssertStatements == true

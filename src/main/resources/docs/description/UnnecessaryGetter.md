*Since CodeNarc 0.12*

Checks for explicit calls to getter/accessor methods which can, for the
most part, be replaced by property access. A getter is defined as a
no-argument method call that matches `get[A-Z]` but not `getClass()` or
`get[A-Z][A-Z]` such as `getURL()`.

Calls to getter methods within Spock method calls `Mock()`, `Stub()` and
`Spy()` are ignored.

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
<td>checkIsMethods</td>
<td>If <code>true</code>, then also check isXxx() getters methods.</td>
<td><code>true</code></td>
</tr>
<tr>
<td>ignoreMethodNames</td>
<td>Specifies one or more (comma-separated) method names that should be
ignored (i.e., that should not cause a rule violation). The names may
optionally contain wildcards (*,?).</td>
<td><code>null</code></td>
</tr>
</tbody>
</table>

These bits of code produce violations:

        x.getProperty()
        x.getFirst()
        x.getFirstName()
        x.getA()

        x.isFirst()         // Violation if checkIsMethods is true
        x.isA()             // Violation if checkIsMethods is true

These bits of code do not:

        x.property
        x.first
        x.firstName
        x.a
        x.getURL()
        x.getClass()
        x.getProperty('key')

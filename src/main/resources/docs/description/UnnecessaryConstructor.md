*Since CodeNarc 0.11*

This rule detects when a constructor is not necessary; i.e., when
there’s only one constructor, it’s `public`, has an empty body, and
takes no arguments, or else contains only a single call to `super()`.

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
<td>ignoreAnnotations</td>
<td>If <code>true</code>, then do not report violations if a constructor
has one or more annotations.</td>
<td><code>false</code></td>
</tr>
</tbody>
</table>

Example of violations:

        class MyClass {
            public MyClass() {          // violation; constructor is not necessary
            }
        }

        class MyClass2 extends OtherClass {
            MyClass2() {                // violation; constructor is not necessary
                super()
            }
        }

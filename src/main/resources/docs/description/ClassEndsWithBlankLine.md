*Since CodeNarc 1.3*

Check whether the class ends with a blank line. By default, it enforces
that there must be a blank line before the closing class brace, except:

- If the class is synthetic (generated)
- If the class is empty and is written in a single line
- If the class is a Script class

A blank line is defined as any line that does not contain any visible
characters.

This rule can be configured with the following properties:

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
<td>ignoreSingleLineClasses</td>
<td>a boolean property to forbid single line classes.If it is false,then
single line classes are considered a violation.</td>
<td><code>true</code></td>
</tr>
<tr>
<td>ignoreInnerClasses</td>
<td>A boolean property to ignore inner classes. If it is <em>false</em>
then inner classes can cause violations.</td>
<td><code>false</code></td>
</tr>
<tr>
<td>blankLineRequired</td>
<td>a boolean property to define if there may be a blank line before the
closing class brace. If it is false, the last line before the brace must
not be blank. Otherwise, it must be blank.</td>
<td><code>true</code></td>
</tr>
</tbody>
</table>

Example of violations:

If *ignoreSingleLineClasses* is `true` and *blankLineRequired* is `true`

                class Foo {
                    int a

                    void hi() {
                    }
                }

If *ignoreSingleLineClasses* is `false` and *blankLineRequired* is
`true`

                class Foo extends Bar*String* { }

If *ignoreSingleLineClasses* is `true` and *blankLineRequired* is
`false`

                class Foo {
                    int a

                    void hi() {
                    }

                }

If *ignoreSingleLineClasses* is `false` and *blankLineRequired* is
`false`

                class Foo {
                    int a

                    void hi() {
                    }

                }

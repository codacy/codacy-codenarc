*Since CodeNarc 1.3*

Check whether the class starts with a blank line. By default, it
enforces that there must be a blank line after the opening class brace,
except:

-   If the class is synthetic (generated)

-   If the class is empty and is written in a single line

-   If the class is a Script class

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
<tr class="header">
<th>Property</th>
<th>Description</th>
<th>Default Value</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td>ignoreSingleLineClasses</td>
<td>A boolean property to ignore single line classes. If it is false, then single line classes are considered a violation.</td>
<td><code>true</code></td>
</tr>
<tr class="even">
<td>ignoreInnerClasses</td>
<td>A boolean property to ignore inner classes. If it is <em>false</em> then inner classes can cause violations.</td>
<td><code>false</code></td>
</tr>
<tr class="odd">
<td>blankLineRequired</td>
<td>A boolean property to define if there may be a blank line after the opening class brace. If it is false, the first content after the brace must not be a blank line. Otherwise, it must be a blank line.</td>
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

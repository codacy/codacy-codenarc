*Since CodeNarc 0.20*

Check for proper formatting of whitespace around colons for literal Map
entries. By default, no whitespace is allowed either before or after the
Map entry colon, but you can change that through the configuration
properties below.

Does not check *spread map* operator, e.g. `def binding = [*: map]`

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
<td>characterBeforeColonRegex</td>
<td>The regular expression that must match the character before the colon (:) for a literal <em>Map</em> entry. For example, <code>/\S/</code> matches any non-whitespace character and <code>/\s/</code> matches any whitespace character (thus requiring a space or whitespace).</td>
<td><code>/\S/</code> (i.e., no space allowed before the colon)</td>
</tr>
<tr class="even">
<td>characterAfterColonRegex</td>
<td>The regular expression that must match the character after the colon (:) for a literal <em>Map</em> entry. For example, <code>/\S/</code> matches any non-whitespace character and <code>/\s/</code> matches any whitespace character (thus requiring a space or whitespace).</td>
<td><code>/\S/</code> (i.e., no space allowed after the colon)</td>
</tr>
</tbody>
</table>

Example of violations:

        Map m1 = [myKey : 12345]            // violation (both before and after the colon)
        println [a :[1:11, 2:22],           // violation on a (before colon)
                    b:[(Integer): 33]]      // violation on Integer (after colon)

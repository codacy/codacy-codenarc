*Since CodeNarc 1.3*

Checks for empty *@see* tags within javadoc.

Known limitation: Only the first occurrence of an empty @see within a
javadoc comment is found.

NOTE: This is a file-based rule, rather than an AST-based rule, so the
*applyToClassNames* and *doNotApplyToClassNames* rule configuration
properties are not available. See [Standard Properties for Configuring
Rules](./codenarc-configuring-rules.html#standard-properties-for-configuring-rules).

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
<td>allowMultiline</td>
<td>Set to <code>true</code> to allow the tag content (name, description, etc.) to start on the following line. If <em>false</em>, that content must start on the same line as the tag.</td>
<td><code>false</code></td>
</tr>
</tbody>
</table>

Example of violations:

        /**
         * Sample class
         *
         * @see                                                         // violation
         */
        class MyClass {

            /**
             * Return the calculated count of some stuff,
             * starting with the specified startIndex.
             *
             * @param startIndex - the starting index
             * @return the full count
             * @throws RuntimeException when you least expect it
             *     @see                                                 // violation
             *
             * NOTE: Only the first occurrence of an empty @see tag
             *       within a javadoc comment is found, so the
             *       following line is not flagged as a violation!!!
             * @see
             */
            int countThings(int startIndex) { }

            /**
             *@see                                                      // violation
             */
            String name = 'joe'
        }

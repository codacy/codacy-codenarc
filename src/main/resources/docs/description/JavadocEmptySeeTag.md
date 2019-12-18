
*Since CodeNarc 1.3*

Checks for empty *@see* tags within javadoc.

Known limitation: Only the first occurrence of an empty @see within a javadoc comment is found.

NOTE: This is a file-based rule, rather than an AST-based rule, so the *applyToClassNames* and
*doNotApplyToClassNames* rule configuration properties are not available. See
[Standard Properties for Configuring Rules](./codenarc-configuring-rules.html#standard-properties-for-configuring-rules).

| Property                    | Description            | Default Value    |
|-----------------------------|------------------------|------------------|
| allowMultiline              | Set to `true` to allow the tag content (name, description, etc.) to start on the following line. If *false*, that content must start on the same line as the tag.  | `false` |

Example of violations:

```
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
```

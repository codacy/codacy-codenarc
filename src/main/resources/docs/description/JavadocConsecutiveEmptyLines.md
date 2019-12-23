
*Since CodeNarc 1.3*

Checks for javadoc comments with more than one consecutive empty line.

Known limitation: Only the first occurrence of consecutive empty lines within a javadoc comment is found.

NOTE: This is a file-based rule, rather than an AST-based rule, so the *applyToClassNames* and
*doNotApplyToClassNames* rule configuration properties are not available. See
[Standard Properties for Configuring Rules](./codenarc-configuring-rules.html#standard-properties-for-configuring-rules).

Example of violations:

```
    /**
     * Description
     *
     *                                                                          // violation
     * @param startIndex - the starting index
     * @return the full count
     * @throws RuntimeException if you are not pure of spirit
     *
     * NOTE: Only the first occurrence of consecutive empty lines
     *       within a javadoc comment is found, so the following
     *       lines are not flagged as violations!!!
     *
     *
     */
    int countThings(int startIndex) { }
```

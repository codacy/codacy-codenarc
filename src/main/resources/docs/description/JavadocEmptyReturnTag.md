
*Since CodeNarc 1.3*

Checks for empty @return tags within javadoc.

Known limitation: Only the first occurrence of an empty @return within a javadoc comment is found.

NOTE: This is a file-based rule, rather than an AST-based rule, so the *applyToClassNames* and
*doNotApplyToClassNames* rule configuration properties are not available. See
[Standard Properties for Configuring Rules](./codenarc-configuring-rules.html#standard-properties-for-configuring-rules).

| Property                    | Description            | Default Value    |
|-----------------------------|------------------------|------------------|
| allowMultiline              | Set to `true` to allow the tag content (name, description, etc.) to start on the following line. If *false*, that content must start on the same line as the tag.  | `false` |

Example of violations:

```
    /**
     * Return the calculated count of some stuff.
     *
     * @param startIndex - the starting index
     * @return                                  // violation
     * @throws RuntimeException if you don't say "please"
     */
    int countThings(int startIndex) { }
```
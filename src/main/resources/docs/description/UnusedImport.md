
Checks for *import* statements for classes that are never referenced within the source file. Also
checks static imports.

Known limitations:
  * Does not check for unused imports containing wildcards (e.g. `import org.codenarc.*`)
  * Misses unused imports if the class/alias name is contained within strings, comments or other (longer)
    names (i.e., if that string shows up almost anywhere within the source code).

NOTE: This is a file-based rule, rather than a typical AST-based rule, so the *applyToClassNames*
and *doNotApplyToClassNames* rule configuration properties are not available. See
[Standard Properties for Configuring Rules](./codenarc-configuring-rules.html#standard-properties-for-configuring-rules).
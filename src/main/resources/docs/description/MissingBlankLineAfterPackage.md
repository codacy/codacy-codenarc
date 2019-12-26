
*Since CodeNarc 0.21*

Makes sure there is a blank line after the package statement of a source code file.

Example of violation:

```
  package org.codenarc
  import java.util.Date                     // violation

  class MyClass {
      void go() { /* ... */ }
  }
```

NOTE: This is a file-based rule, rather than an AST-based rule, so the *applyToClassNames* and
*doNotApplyToClassNames* rule configuration properties are not available. See
[Standard Properties for Configuring Rules](./codenarc-configuring-rules.html#standard-properties-for-configuring-rules).

*Since CodeNarc 0.21*

Makes sure there is a blank line after the imports of a source code
file.

Example of violation:

        import org.apache.commons.lang.StringUtils
        class MyClass { }                       // violation

NOTE: This is a file-based rule, rather than an AST-based rule, so the
*applyToClassNames* and *doNotApplyToClassNames* rule configuration
properties are not available. See \[Standard Properties for Configuring
Rules\](./codenarc-configuring-rules.htm

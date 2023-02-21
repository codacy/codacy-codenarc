*Since CodeNarc 0.21*

Makes sure there are no consecutive lines that are either blank or
whitespace only. This reduces the need to scroll further than necessary
when reading code, and increases the likelihood that a logical block of
code will fit on one screen for easier comprehension.

Known Limitations:

- Will create *false positive* violation for consecutive blank lines
  within string literals. For those cases, configure the rule to skip
  that file (using *doNotApplyToFileNames* or
  *doNotApplyToFilesMatching* rule properties). See [Standard Properties
  for Configuring
  Rules](https://codenarc.org/codenarc-configuring-rules.html#standard-properties-for-configuring-rules).
  Note that the class-based rule properties are not available on this
  rule.

Example of violation:

        def name


        def value



        def id

NOTE: This is a file-based rule, rather than an AST-based rule, so the
*applyToClassNames* and *doNotApplyToClassNames* rule configuration
properties are not available. See \[Standard Properties for Configuring
Rules\](./codenarc-configuring-rules.htm

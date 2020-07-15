Checks for an *import* from any package that is already automatically
imported for Groovy files. A Groovy file does not need to include an
import for classes from *java.lang*, *java.util*, *java.io*, *java.net*,
*groovy.lang* and *groovy.util*, as well as the classes
*java.math.BigDecimal* and *java.math.BigInteger*.

NOTE: This is a file-based rule, rather than a typical AST-based rule,
so the *applyToClassNames* and *doNotApplyToClassNames* rule
configuration properties are not available. See [Standard Properties for
Configuring
Rules](./codenarc-configuring-rules.html#standard-properties-for-configuring-rules).

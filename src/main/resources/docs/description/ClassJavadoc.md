
*Since CodeNarc 0.15*

Makes sure each class and interface definition is preceded by javadoc. Enum definitions are not checked, due to strange
behavior in the Groovy AST. By default, only the main class in a file is checked for Javadoc. The main class is defined as
the class that has the same name as the source file, for instance MyClass is the main class in MyClass.groovy but the class
MyOtherClass defined in the same source file is not the main class. To check all the classes in the file set the rule
property `applyToNonMainClasses` to true.

NOTE: This is a file-based rule, rather than an AST-based rule, so the *applyToClassNames* and
*doNotApplyToClassNames* rule configuration properties are not available. See
[Standard Properties for Configuring Rules](./codenarc-configuring-rules.html#standard-properties-for-configuring-rules).

Checks for non-`final` fields on a Grails service class. Grails service
classes are singletons by default, and so they should be reentrant. In
most cases, this implies (or at least encourages) that they should be
stateless.

This rule ignores (i.e., does not cause violations for) the following:
\* All `final` fields (either instance or static). Note that fields that
are `static` and non-`final`, however, do cause a violation. \*
Non-`static` properties (i.e., no visibility modifier specified)
declared with `def`. \* All classes annotated with the `@Immutable`
transformation. See \[Immutable
transformation\](https://groovy-lang.org/releasenotes/groovy-1.6.htm

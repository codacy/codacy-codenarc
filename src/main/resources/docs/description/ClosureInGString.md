*Since CodeNarc 3.5.0*

Closures are CPS transformed by Jenkins and will cause an error at
runtime when used in GStrings. Typically they can be replaced by
variable interpolation. Closures that are used as argument to method
calls on the other hand are fine. See also the Jenkins docs about
\[Closures inside
GString\](https://www.jenkins.io/doc/book/pipeline/cps-method-mismatches

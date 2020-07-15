*Since CodeNarc 0.11*

A **serialVersionUID** is normally intended to be used with
Serialization. It needs to be of type `long`, `static`, and `final`.
Also, it should be declared `private`. Providing no modifier creates a
*Property* and Groovy generates a *getter*, which is probably not
intended.

From API javadoc for `java.io.Serializable`: *It is also strongly
advised that explicit serialVersionUID declarations use the private
modifier where possible, since such declarations apply only to the
immediately declaring class–serialVersionUID fields are not useful as
inherited members.*

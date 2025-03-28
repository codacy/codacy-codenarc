*Since CodeNarc 3.5.0*

Every expression/variable in a CPS transformed method in Jenkins can
potentially be serialized and should therefore implement the
`Serializable` interface.

Note: The interfaces `List`, `Map` and `Set` are treated as Serializable
because nearly every implementation is `Serializable`. It would be bad
codestyle to use the implementation as type (see \[ImplementationAsType
Rule\](./codenarc-rules-design.htm

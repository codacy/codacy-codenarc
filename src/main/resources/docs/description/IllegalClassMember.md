
*Since CodeNarc 0.19*

Checks for classes containing fields/properties/methods matching configured illegal member
modifiers or not matching any of the configured allowed member modifiers.

| Property                    | Description            | Default Value    |
|-----------------------------|------------------------|------------------|
| allowedFieldModifiers       | Specifies one or more groups of whitespace-delimited modifier names (e.g. "public static" or "protected"). Multiple groups are separated by commas (e.g. "private final, protected"). If a field does not match all of the modifiers in any group, then trigger a violation. If `null` or empty, skip this check.  | `null` |
| allowedMethodModifiers      | Specifies one or more groups of whitespace-delimited modifier names (e.g. "public static" or "protected"). Multiple groups are separated by commas (e.g. "private final, protected"). If a method does not match all of the modifiers in any group, then trigger a violation. If `null` or empty, skip this check.  | `null` |
| allowedPropertyModifiers    | Specifies one or more groups of whitespace-delimited modifier names (e.g. "public static" or "protected"). Multiple groups are separated by commas (e.g. "private final, protected"). If a property does not match all of the modifiers in any group, then trigger a violation. If `null` or empty, skip this check.  | `null` |
| ignoreMethodNames           | Specifies one or more (comma-separated) method names that should be ignored (i.e., that should not cause a rule violation). The names may optionally contain wildcards (*,?).  | `null` |
| ignoreMethodsWithAnnotationNames | Specifies one or more (comma-separated) annotation names that should be ignored (i.e., methods with those annotations should not cause a rule violation). The names may optionally contain wildcards (*,?). (Do not include the "@" in the annotation name.| `null` |
| illegalFieldModifiers       | Specifies one or more groups of whitespace-delimited modifier names (e.g. "public static" or "protected"). Multiple groups are separated by commas (e.g. "private final, protected"). If a field matches all of the modifiers in any group, then trigger a violation. If `null` or empty, skip this check.  | `null` |
| illegalMethodModifiers      | Specifies one or more groups of whitespace-delimited modifier names (e.g. "public static" or "protected"). Multiple groups are separated by commas (e.g. "private final, protected"). If a method matches all of the modifiers in any group, then trigger a violation. If `null` or empty, skip this check.  | `null` |
| illegalPropertyModifiers    | Specifies one or more groups of whitespace-delimited modifier names (e.g. "public static" or "protected"). Multiple groups are separated by commas (e.g. "private final, protected"). If a property matches all of the modifiers in any group, then trigger a violation. If `null` or empty, skip this check.  | `null` |

Modifiers for fields and methods include:
  * public
  * protected
  * private
  * static
  * final
  * volatile (fields only)
  * transient (fields only)

Modifiers for properties are only:
  * static
  * final

Note that you must use the standard rule properties, such as `applyToClassNames`, `doNotApplyToFileNames`
and `applyToFilesMatching` to apply this rule to a subset of all classes/files. These rule properties
are described in 
[Standard Properties for Configuring Rules](./codenarc-configuring-rules.htm
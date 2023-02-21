*Since CodeNarc 2.1*

Checks that there is a blank line before a field declaration that uses
annotations.

Ignore field declarations where: - The previous line contains a
comment - The declaration (annotations) start on the first line of the
class - All annotations are on the same line as the field declaration.

Examples of violations:

        class MyClass {
            // No violations for field declarations preceded by a comment
            @Delegate
            AutoCloseable stream
            
            String publicField                  // violation
            @PackageScope
            String packageScopedField
        }

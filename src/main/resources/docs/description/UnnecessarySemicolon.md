*Since CodeNarc 0.13*

Semicolons as line terminators are not required in Groovy: remove them.
Do not use a semicolon as a replacement for empty braces on for and
while loops; this is a confusing practice.

The rule contains a String property called ‘excludePattern’. Any source
code line matching this pattern will not trigger a violation. The
default value is ’\s?\\*.*|/\\*.*|.*//.*|.*\\*/.\*’ This is to filter
out comments. Any source line that even looks like it is a comment is
ignored.

- `\s?\*.*` == whitespace plus star character plus anything
- `/\*.*` == any line that contains the /\* sequence
- `.*//.*` == any line that contains the // sequence
- `.*\*/.*` == any line that contains the \*/ sequence

Example of violations:

        package my.company.server;  // violation

        import java.lang.String;    // violation

        println(value) ;             // violation

        for (def x : list);         // violation

        // this code is OK
        println(value); println (otherValue)

Known limitations: - Will not flag a semicolon on a field declaration
with no initial value specified when running on Groovy 3.x.

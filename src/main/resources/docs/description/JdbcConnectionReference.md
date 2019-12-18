
*Since CodeNarc 0.15*

Checks for direct use of `java.sql.Connection`, which is discouraged and almost never necessary
in application code.

For a more *Groovy* alternative, see <http://groovy-lang.org/databases.html> for information on the
**Groovy Sql** abstraction layer for JDBC/SQL.

Note: If a violation is triggered from an **import** statement, then you may get multiple violations per
import if there are multiple classes in the source file. In that case, the imports are processed once per class.



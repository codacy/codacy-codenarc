
*Since CodeNarc 0.15*

Checks for direct use of `java.sql.ResultSet`, which is not necessary if using the Groovy **Sql** facility or an
ORM framework such as *Hibernate*.

See <http://groovy-lang.org/databases.html> for information on the **Groovy Sql** abstraction
layer for JDBC/SQL.

Note: If a violation is triggered from an **import** statement, then you may get multiple violations per
import if there are multiple classes in the source file. In that case, the imports are processed once per class.


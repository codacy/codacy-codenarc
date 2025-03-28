*Since CodeNarc 0.19*

Forbids usage of SQL reserved keywords as class or field names in Grails
domain classes. Naming a domain class (or its field) with such a keyword
causes SQL schema creation errors and/or redundant table/column name
mappings.

Note: due to limited type information available during CodeNarc’s
operation, this rule will report fields of type `java.io.Serializable`,
but not of its implementations. Please specify any implementations used
as domain properties in `additionalHibernateBasicTypes`.

<table>
<colgroup>
<col style="width: 40%" />
<col style="width: 33%" />
<col style="width: 25%" />
</colgroup>
<thead>
<tr>
<th>Property</th>
<th>Description</th>
<th>Default Value</th>
</tr>
</thead>
<tbody>
<tr>
<td>additionalHibernateBasicTypes</td>
<td>Comma-separated list of simple class names of additional classes
that Hibernate maps as basic types (creates a column for a field of such
class). Add your custom basic types here.</td>
<td><code>''</code></td>
</tr>
<tr>
<td>additionalReservedSqlKeywords</td>
<td>Comma-separated list of additional reserved SQL keywords (just in
case the 337 keywords of nowadays SQL-* standards weren’t enough)</td>
<td><code>''</code></td>
</tr>
</tbody>
</table>

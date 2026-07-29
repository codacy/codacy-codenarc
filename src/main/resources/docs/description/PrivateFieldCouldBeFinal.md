*Since CodeNarc 0.17*

This rule finds `private` fields that are only set within a
*constructor* or *field initializer*. Such fields can safely be made
`final`.

<table>
<colgroup>
<col style="width: 40%" />
<col style="width: 33%" />
<col style="width: 25%" />
</colgroup>
<thead>
<tr class="header">
<th>Property</th>
<th>Description</th>
<th>Default Value</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td>ignoreFieldNames</td>
<td>Specifies one or more (comma-separated) field names that should be
ignored (i.e., that should not cause a rule violation). The names may
optionally contain wildcards (*,?).</td>
<td><code>null</code></td>
</tr>
<tr class="even">
<td>ignoreJpaEntities</td>
<td>Specifies whether fields defined inside classes annotated with <span
class="citation" data-cites="Entity">@Entity</span> or <span
class="citation" data-cites="MappedSuperclass">@MappedSuperclass</span>
JPA annotations should be ignored (i.e., that should not cause a rule
violation).</td>
<td><code>false</code></td>
</tr>
</tbody>
</table>

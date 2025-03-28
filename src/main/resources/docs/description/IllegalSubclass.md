*Since CodeNarc 0.21*

Checks for classes that extend one of the specified set of illegal
superclasses.

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
<td>superclassNames</td>
<td>Specifies the comma-separated list of (fully-qualified) class names.
The class name(s) may optionally include wildcard characters (‘<em>’ or
’?’). Note that the ’</em>’ wildcard matches any sequence of zero or
more characters in the class/package name, e.g. ’a.*.MyClass’ matches
<code>a.b.MyClass</code> as well as <code>a.b.c.d.MyClass</code>. If
<code>classNames</code> is null or empty, do nothing.</td>
<td><code>null</code></td>
</tr>
</tbody>
</table>

A RuleSet can contain any number of instances of this rule, but each
should be configured with a unique rule *name* and *string*, and
(optionally) customized *violationMessage* and *priority*.

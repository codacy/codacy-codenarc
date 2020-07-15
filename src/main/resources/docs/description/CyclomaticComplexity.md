Calculates the *Cyclomatic Complexity* for methods/classes and checks
against configured threshold values.

The **maxMethodComplexity** property holds the threshold value for the
cyclomatic complexity value for each method. If this value is non-zero,
a method with a cyclomatic complexity value greater than this value is
considered a violation.

The **maxClassAverageMethodComplexity** property holds the threshold
value for the average cyclomatic complexity value for each class. If
this value is non-zero, a class with an average cyclomatic complexity
value greater than this value is considered a violation.

This rule treats “closure fields” as methods. If a class field is
initialized to a Closure (ClosureExpression), then that Closure is
analyzed and checked just like a method.

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
<td>maxMethodComplexity</td>
<td>The maximum <em>cyclomatic complexity</em> value allowed for a single method (or “closure field”). If zero or <em>null</em>, then do not check method-level complexity.</td>
<td>20</td>
</tr>
<tr class="even">
<td>maxClassAverageMethodComplexity</td>
<td>The maximum average <em>cyclomatic complexity</em> value allowed for a class, calculated as the average complexity of its methods or “closure fields”. If zero or <em>null</em>, then do not check average class-level complexity.</td>
<td>20</td>
</tr>
<tr class="odd">
<td>maxClassComplexity</td>
<td>The maximum total <em>cyclomatic complexity</em> value allowed for a class, calculated as the total complexity of its methods or “closure fields”. If zero or <em>null</em>, then do not check total class-level complexity.</td>
<td>0</td>
</tr>
<tr class="even">
<td>ignoreMethodNames</td>
<td>Specifies one or more (comma-separated) method names that that should not cause a rule violation. The names may optionally contain wildcards (*,?). Note that the ignored methods still contribute to the class complexity value.</td>
<td><code>null</code></td>
</tr>
</tbody>
</table>

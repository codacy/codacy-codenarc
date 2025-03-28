Calculates the *ABC* size metric for methods/classes and checks against
configured threshold values.

The **maxMethodAbcScore** property holds the threshold value for the ABC
score for each method. If this value is non-zero, a method with an ABC
score greater than this value is considered a violation. The value does
not have to be an integer (e.g., 1.7 is allowed).

The **maxClassAverageMethodAbcScore** property holds the threshold value
for the average ABC score for each class. If this value is non-zero, a
class with an average ABC score value greater than this value is
considered a violation. The value does not have to be an integer.

The **maxClassAbcScore** property holds the threshold value for the
total ABC score value for each class. If this value is non-zero, a class
with a total ABC score greater than this value is considered a
violation. The value does not have to be an integer.

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
<tr>
<th>Property</th>
<th>Description</th>
<th>Default Value</th>
</tr>
</thead>
<tbody>
<tr>
<td>maxMethodAbcScore</td>
<td>The maximum <em>ABC</em> score allowed for a single method (or
“closure field”). If zero or <em>null</em>, then do not check
method-level scores.</td>
<td>60</td>
</tr>
<tr>
<td>maxClassAverageMethodAbcScore</td>
<td>The maximum average <em>ABC</em> score allowed for a class,
calculated as the average score of its methods or “closure fields”. If
zero or <em>null</em>, then do not check class-level average
scores.</td>
<td>60</td>
</tr>
<tr>
<td>maxClassAbcScore</td>
<td>The maximum <em>ABC</em> score allowed for a class, calculated as
the total ABC score of its methods or “closure fields”. If zero or
<em>null</em>, then do not check class-level scores.</td>
<td>0</td>
</tr>
<tr>
<td>ignoreMethodNames</td>
<td>Specifies one or more (comma-separated) method names that that
should not cause a rule violation. The names may optionally contain
wildcards (*,?). Note that the ignored methods still contribute to the
class complexity value.</td>
<td><code>null</code></td>
</tr>
</tbody>
</table>

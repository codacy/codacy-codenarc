Calculates the
[C.R.A.P.](http://www.artima.com/weblogs/viewpost.jsp?thread=210575)
(Change Risk Anti-Patterns) metric score for methods/classes and checks
against configured threshold values.

The *CRAP* metric score is based on the *cyclomatic complexity* and test
coverage for individual methods. A method with a *CRAP* value greater
than the **maxMethodCrapScore** property causes a violation. Likewise, a
class that has an (average method) *CRAP* value greater than the
**maxClassAverageMethodCrapScore** property causes a violation.

**NOTE:** This rule requires the **GMetrics**\[3\] jar, version 0.5 (or
later), on the classpath, as well as a **Cobertura**\[4\]-\[6\] XML
coverage file. If either of these prerequisites is not available, this
rule logs a warning messages and exits (i.e., does nothing).

The **maxMethodCrapScore** property holds the threshold value for the
CRAP value for each method. If this value is non-zero, a method with a
cyclomatic complexity value greater than this value is considered a
violation.

The **maxClassAverageMethodCrapScore** property holds the threshold
value for the average CRAP value for each class. If this value is
non-zero, a class with an average cyclomatic complexity value greater
than this value is considered a violation.

NOTE: This rule does NOT treat *closure fields* as methods (unlike some
of the other size/complexity rules).

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
<td>coberturaXmlFile</td>
<td>The path to the Cobertura XML coverage file for the Groovy code By
default, the path is relative to the classpath. But the path may be
optionally prefixed by any of the valid java.net.URL prefixes, such as
“file:” (to load from a relative or absolute path on the filesystem), or
“http:”. This property is REQUIRED.</td>
<td><code>null</code></td>
</tr>
<tr>
<td>maxMethodCrapScore</td>
<td>The maximum <em>CRAP</em> metric value allowed for a single method.
If zero or <em>null</em>, then do not check method-level
complexity.</td>
<td>30</td>
</tr>
<tr>
<td>maxClassAverageMethodCrapScore</td>
<td>The maximum <em>CRAP</em> average metric value allowed for a class,
calculated as the average CRAP value of its methods. If zero or
<em>null</em>, then do not check the average class-level CRAP
value.</td>
<td>30</td>
</tr>
<tr>
<td>maxClassCrapScore</td>
<td>The maximum total <em>CRAP</em> metric value allowed for a class,
calculated as the total CRAP value of its methods. If zero or
<em>null</em>, then do not check class-level CRAP value.</td>
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

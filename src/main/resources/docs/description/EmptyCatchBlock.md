Checks for empty *catch* blocks. In most cases, exceptions should not be
caught and ignored (swallowed).

The rule has a property named `ignoreRegex` that defaults to the value
‘ignore|ignored’. If the name of the exception matches this regex then
no violations are produced.

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
<td>ignoreRegex</td>
<td>Regular expression - exception parameter names matching this regular
expression are ignored and no violations are produced.</td>
<td>‘ignore|ignored’</td>
</tr>
</tbody>
</table>

Here is an example of code that produces a violation:

        def myMethod() {
            try {
                doSomething
            } catch(MyException e) {                //violation
                // should do something here
            }
        }

        def myMethod() {
            try {
                doSomething
            } catch(MyException ignored) {
                //no violations because the parameter name is ignored
            }
        }

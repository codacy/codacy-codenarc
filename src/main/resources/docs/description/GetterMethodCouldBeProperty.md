*Since CodeNarc 0.16*

If a class defines a `public` method that follows the Java getter
notation and that returns a constant, literal or static final field
value, then it is cleaner to provide a Groovy property for the value
rather than a Groovy method.

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
<td>ignoreMethodsWithOverrideAnnotation</td>
<td>If <code>true</code>, then do not check methods annotated with <span
class="citation" data-cites="Override">@Override</span>.</td>
<td><code>false</code></td>
</tr>
</tbody>
</table>

Example of violations:

        interface Parent {
            String getSomething()
            String getSomethingElse()
        }

        class Child extends Parent {
            static final VALUE = 'value'

            String getSomething() {         // violation
                'something'         
            }

            @Override
            String getSomethingElse() {     // violation
                VALUE       
            }

            int getOtherValue() {           // violation
                123
            }

            Class getTheClass() {           // violation
                return Integer
            }

            static String getName() {       // violation
                'MyName'
            }
        }

        class Child2 extends Parent {
            static final VALUE = 'value'
            final String something = 'something'    // this is cleaner
            final String somethingElse = VALUE      // this is cleaner
            final int otherValue = 123              // this is cleaner
            static final String name = 'MyName'     // this is cleaner
        }

Checks for blocks or closures nested more deeply than a configured
maximum number. Blocks include `if`, `for`, `while`, `switch`, `try`,
`catch`, `finally` and `synchronized` blocks/statements, as well as
closures.

Methods calls, constructor calls, and property access through Builder
objects are ignore. For instance, this code does not cause a violation:

``` 
    myBuilder.root {
        foo {
            bar {
                baz {
                    quix {
                        qux {
                            quaxz {
                            }
                        }
                    }
                }
            }
        }
    }
```

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
<td>maxNestedBlockDepth</td>
<td>The maximum number of nesting levels. A block or closure nested deeper than that number of levels is considered a violation.</td>
<td>5</td>
</tr>
<tr class="even">
<td>ignoreRegex</td>
<td>Determines what is a builder call. For instance, closures nested on a method named createBuilder, a property named myBuilder, or a constructor call to object MyBuilder() do not produce violations.</td>
<td>.*(b|B)uilder</td>
</tr>
</tbody>
</table>

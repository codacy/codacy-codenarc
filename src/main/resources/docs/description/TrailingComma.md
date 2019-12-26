
*Since CodeNarc 0.25*

Check whether list and map literals contain optional trailing comma.
Rationale: Putting this comma in make is easier
to change the order of the elements or add new elements on the end.

| Property                    | Description            | Default Value    |
|-----------------------------|------------------------|------------------|
| checkList                   | To disable checking List literals, set this property to `false` | `true` |
| checkMap                    | To disable checking Map literals, set this property to `false`  | `true` |
| ignoreSingleElementList     | If true, skip checking Lists that have only a single element.   | `true` |
| ignoreSingleElementMap      | If true, skip checking Maps that have only a single element.    | `true` |

This is valid code:

```
  int[] array1 = [] // one line declaration
  int[] array2 = [ // empty list
                 ]
  int[] array3 = [1,2,3] // one line declaration
  int[] array4 = [1,
                  2,
                  3, // contains trailing comma
                 ]
```

Example of violations:

```
  int[] array2 = [1,
                  2 // there is no trailing comma
                 ]
```

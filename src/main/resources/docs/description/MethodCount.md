
*Since CodeNarc 0.11*

Checks if the number of methods within a class exceeds the number of lines specified by the **maxMethod** property.

A class with too many methods is probably a good suspect for refactoring, in order to reduce its
complexity and find a way to have more fine grained objects.

| Property                    | Description            | Default Value    |
|-----------------------------|------------------------|------------------|
| maxMethods                  | The maximum number of methods allowed in a class definition.   | 30                     |



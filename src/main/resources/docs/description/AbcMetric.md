
Calculates the *ABC* size metric for methods/classes and checks against configured threshold values.

The **maxMethodAbcScore** property holds the threshold value for the ABC score for each method.
If this value is non-zero, a method with an ABC score greater than this value is considered a violation.
The value does not have to be an integer (e.g., 1.7 is allowed).

The **maxClassAverageMethodAbcScore** property holds the threshold value for the average ABC
score for each class. If this value is non-zero, a class with an average ABC score value greater
than this value is considered a violation. The value does not have to be an integer.

The **maxClassAbcScore** property holds the threshold value for the total ABC
score value for each class. If this value is non-zero, a class with a total ABC score
greater than this value is considered a violation. The value does not have to be an integer.

This rule treats "closure fields" as methods. If a class field is initialized to a Closure (ClosureExpression),
then that Closure is analyzed and checked just like a method.

| Property                    | Description            | Default Value    |
|-----------------------------|------------------------|------------------|
| maxMethodAbcScore             | The maximum *ABC* score allowed for a single method (or "closure field"). If zero or *null*, then do not check method-level scores. | 60 |
| maxClassAverageMethodAbcScore | The maximum average *ABC* score allowed for a class, calculated as the average score of its methods or "closure fields". If zero or *null*, then do not check class-level average scores. | 60 |
| maxClassAbcScore              | The maximum *ABC* score allowed for a class, calculated as the total ABC score of its methods or "closure fields". If zero or *null*, then do not check class-level scores. | 0 |
| ignoreMethodNames             | Specifies one or more (comma-separated) method names that that should not cause a rule violation. The names may optionally contain wildcards (*,?). Note that the ignored methods still contribute to the class complexity value. | `null` |

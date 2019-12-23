
*Since CodeNarc 0.15*

The Collections.sort() method mutates the list and returns the list as a value. If you are assigning the result of sort() to a
variable, then you probably don't realize that you're also modifying the original list as well. This is frequently the cause of subtle bugs.
This violation is triggered when a sort() method call appears as the right hand side of an assignment, or when it appears
as the first method call in a series of chained method calls.

Example of violations:

```
  def a = myList.sort()
  def b = myList.sort() { it }
  def c = myList.sort().findAll { x * 1 }
```
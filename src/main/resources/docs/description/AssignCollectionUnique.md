
*Since CodeNarc 0.15*

The Collections.unique() method mutates the list and returns the list as a value. If you are assigning the result of unique() to a
variable, then you probably don't realize that you're also modifying the original list as well. This is frequently the cause of subtle bugs.

This violation is triggered when a `unique()` method call that mutates the target collection appears as the right hand side of an assignment,
or when it appears as the first method call in a series of chained method calls.

Example of violations:

```
  def a = myList.unique()                   // No-argument

  def x = myList.unique() { it }            // Single-argument: Closure
  def y = myList.unique { it % 2 }

  def c = myList.unique().findAll { x * 1 } // Chained method call

  def comparator = { o1, o2 -* o1 *=* o2 }
  def x = myList.unique(comparator)         // Single-argument: Comparator

  def x = myList.unique(true)               // Single-argument: boolean true

  def x = myList.unique(true, comparator)   // Two arguments: boolean true and Comparator
  def y = myList.unique(true) { it }        // Two arguments: boolean true and Closure
```


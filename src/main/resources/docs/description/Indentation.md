*Since CodeNarc 1.1*

Check the indentation (spaces only; not tabs) for class, field and
method declarations, and statements.

This rule is limited, and somewhat opinionated. The default is 4 spaces
per indentation level.

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
<td>spacesPerIndentLevel</td>
<td>The number of spaces that make up a single level of
indentation.</td>
<td>4</td>
</tr>
</tbody>
</table>

Known Limitations include:

- Checks spaces only (not tabs)
- Does not check comments
- Does not check line-continuations (i.e., checks only the first line of
  a statement)
- Does not check multiple statements/members on the same line (only
  checks the first one)
- Does not check Map entry expressions
- Does not check List expressions
- Does not check calls to `this()` and `super()` within a constructor
- When classes, methods or fields have annotations, the indentation of
  the annotation is checked, not the actual member. And only the first
  annotation is checked, if there is more than one.

Example of violations:

    // Indent Levels:
    0...1...2...3...4...5

    class MyClass {                                 // CORRECT
        protected int count                         // CORRECT
      private static final NAME = "Joe"             // violation
               def max, min                         // violation on "max" only

     private String doStuff() {                     // violation
            def internalCounts = [1, 4, 2]          // CORRECT
                id.trim()                           // violation
        }

        private void executeOtherOne() {            // CORRECT
            try {
              executeWithArgs(args)                 // violation
            } catch(Throwable t) {
                           t.printStackTrace()      // violation
            }
            finally {
                    closeResources()                // violation
            }
        }
    }

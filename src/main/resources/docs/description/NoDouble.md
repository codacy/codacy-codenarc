*Since CodeNarc 1.5*

Checks for use of the `double` or `java.lang.Double` types, in fields,
variables, method parameters, constructor parameters and method return
types. Prefer using BigDecimal or int or long, when exact calculations
are required. This is due to the limitations and gotchas of the floating
point representation of the *double* type. This is especially important
for monetary calculations.

Some related discussions include:

- **Effective Java**, 2nd edition, by Joshua Bloch, Addison Wesley
  (2008). Item \#48: *Avoid float and double if exact answers are
  required*.
- [Why not use Double or Float to represent
  currency?](https://stackoverflow.com/questions/3730019/why-not-use-double-or-float-to-represent-currency)
- [Why You Should Never Use Float and Double for Monetary
  Calculations](https://dzone.com/articles/never-use-float-and-double-for-monetary-calculatio).

Example of violations:

        class MyClass {
            int count
            double doubleProperty                               // Violation: Property (field) type
            private Double doubleField = 1.2                    // Violation: Field type

            private double calculateAverage() { return 0 }      // Violation: Method return type

            protected void setAverage(Double average) { }       // Violation: Method parameter type

            MyClass(int count, double rating, double factor) {  // Violation: Constructor parameter
                String name = 'abc'
                Double doubleVar = calculateAverage()           // Violation: Variable
                double double1, double2 = 0                     // Violation: Variable
            }
        }

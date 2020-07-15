*Since CodeNarc 1.5*

Checks for use of the `float` or `java.lang.Float` types, in fields,
variables, method parameters, constructor parameters and method return
types. Prefer using BigDecimal or int or long, when exact calculations
are required. This is due to the limitations and gotchas of the floating
point representation of the *float* type. This is especially important
for monetary calculations.

Some related discussions include:

  - **Effective Java**, 2nd edition, by Joshua Bloch, Addison Wesley
    (2008). Item


*Since CodeNarc 0.19*

Reports usages of `org.junit.Assert.assertEquals([message,] expected, actual)` where the `actual` parameter
is a constant or a literal. Most likely it was intended to be the `expected` value.

NOTE: This is a [CodeNarc Enhanced Classpath Rule](./codenarc-enhanced-classpath-rules.html).
It requires **CodeNarc** to have the application classes being analyzed, as well as any referenced classes, on the classpath.

Example of violations:

```
    assertEquals(result, 2)
    assertEquals("Message", result, 2)
    assertEquals(result, 2.3d, 0.5d)
    assertEquals("Message", result, 2.3d, 0.5d)
```


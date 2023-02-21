*Since CodeNarc 0.16*

In many case `collectMany()` yields the same result as
`collect{}.flatten()`. It is easier to understand and more clearly
conveys the intent.

Limitations: - The `collectMany()` method does not recursively flatten
nested collections, unlike `collect().flatten()`. e.g.

         def l = [1, [2, 22], 3]
         println l.collect{ [it, it*2] }.flatten() // [1, 2, 2, 22, 2, 22, 2, 22, 3, 6]
         println l.collectMany{ [it, it*2] }       // [1, 2, [2, 22], [2, 22, 2, 22], 3, 6]

Example of violations:

    def l = [1, 2, 3, 4]

    l.collect{ [it, it*2] }.flatten() // suboptimal

    l.collectMany{ [it, it*2] }       // same functionality, better readability

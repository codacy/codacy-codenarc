*Since CodeNarc 0.13*

Finds empty string literals which are being added. This is an
inefficient way to convert any type to a String.

Examples:

        // do not add empty strings to things
        def a = '' + 123
        def b = method('' + property)

        // these examples are OK and do not trigger violations
        def c = 456.toString()
        def d = property?.toString() ?: ""

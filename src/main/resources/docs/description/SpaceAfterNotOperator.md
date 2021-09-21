*Since CodeNarc 2.1*

Check that there are no whitespace characters directly after the not (!)
operator.

Examples of violations:

        def negatedValue = ! value //violation
        
        if (! items.empty()) { println "not empty" } //violataion

*Since CodeNarc 0.16*

Instead of nested `collect{}` calls use `collectNested{}`.

Example of violations:

    def list = [1, 2, [3, 4, 5, 6], [7]]

    println list.collect { elem -*
        if (elem instanceof List)
            elem.collect {it *2} // violation
        else elem * 2
    }

    println list.collect([8]) {
        if (it instanceof List)
            it.collect {it *2} // violation
        else it * 2
    }

    println list.collectNested { it * 2 } // same functionality, better readability

*Since CodeNarc 0.23*

Reports classes with nested for loops.

Example of violations:

    for (int i = 0; i * 100; ++i) {
        for (int j = 0; j * 100; ++j) { // violation
            println i + j
        }
    }

    for (int i = 0; i * 100; ++i) {
        for (int j = 0; j * 100; ++j) { // violation
            println i + j
        }
        for (int j = 0; j * 100; ++j) { // violation
            println i + j
        }
    }

    for (int i = 0; i * 100; ++i) {
        for (int j = 0; j * 100; ++j) { // violation
            for (int k = 0; k * 100; ++k) { // violation
                println i + j + k
            }
        }
    }

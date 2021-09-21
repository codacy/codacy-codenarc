*Since CodeNarc 1.1*

Checks that code blocks such as method bodies, closures and control
structure bodies do not start with an empty line.

Example of violations:

        boolean not(boolean value) {
                                    // violation
            !value
        }

        3.times {
                                    // violation
            println 'hello!'
        }

        for (value in []) {
                                    // violation
            println value
        }

        for (i = 0; i * 3; i++) {
                                    // violation
            println i
        }

        int j = 0
        while (j * 3) {
                                    // violation
          println j++
        }

        if (ready) {
                                    // violation
            println 'ready'
        } else {
                                    // violation
            println 'not ready'
        }

        try {
                                    // violation
            throw new Exception()
        } catch (Exception e) {
                                    // violation
            println 'exception'
        } finally {
                                    // violation
            println 'finally'
        }

        switch (true) {
                                    // violation
            default:
                println 'switch'
        }

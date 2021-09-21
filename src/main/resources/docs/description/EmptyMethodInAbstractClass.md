*Since CodeNarc 0.12*

An empty method in an abstract class should be abstract instead, as
developer may rely on this empty implementation rather than code the
appropriate one.

        abstract class MyClass {
            def couldBeAbstract_1() {
                return null  // Should be abstract method
            }

            void couldBeAbstract_2() {
                // Should be abstract method
            }
        }

\<Since CodeNarc 3.3.0\>

Checks that there is whitespace before comment characters (// and /\*)

Notes: - Comments are not available as part of the Groovy AST, so this
rule is implemented using regular expression searches. The rule uses
some heuristics to avoid false positives, but can also miss some actual
violations (i.e., false negatives).

Example of violations:

        import org.acme.Stuff/**                            // violation
         *    @author Some Developer
         */
        class MyClass {// class comment                     // violation
            /**
             *Return the calculated count of some stuff,
             */
            int countThings(int startIndex) {// comment     // violation
                int count = 3 +// first part                // violation
                reduce(23)
                amount = 3 + amount/*violation*/            // violation
            }//comment                                      // violation
            
            int count = 3//Other comment                    // violation
            String x = doStuff()/*Single line comment*/     // violation
        }

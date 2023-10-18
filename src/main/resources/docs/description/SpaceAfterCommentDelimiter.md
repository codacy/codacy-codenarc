\<Since CodeNarc 3.3.0\>

Checks that there is whitespace after comment delimiter (// and /\*).

Notes: - Comments are not available as part of the Groovy AST, so this
rule is implemented using regular expression searches. The rule uses
some heuristics to avoid false positives, but can also miss some actual
violations (i.e., false negatives). - May cause violation for
commented-out code (if there is no space after the comment delimiter)

Example of violations:

        /**Bad1                                             // violation
         *Ignored .. not a violation
         *    @author Some Developer
         */
        class MyClass {//Bad4                               // violation
            int countThings(int start) { //some comment     // violation
                //Do stuff                                  // violation
            } //comment                                     // violation
        }
        //Other comment                                     // violation
        /*Single line comment*/                             // violation

*Since CodeNarc 2.1*

Checks that there is no whitespace after the method name when a method
call contains parenthesis.

Examples of violations:

        aMethod ("arg")         // violation
        
        throw new Exception ()  // violation

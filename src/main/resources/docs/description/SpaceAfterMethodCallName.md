*Since CodeNarc 2.1*

Checks that there is no whitespace after the method name when a method
call contains parenthesis or that there is at most one space after the
method name if the call does not contain parenthesis.

Examples of violations:

        aMethod ("arg") //violation
        
        aMethod  "arg" //violation
        
        throw new Exception () //violation
        

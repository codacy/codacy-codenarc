*Since CodeNarc 2.1*

Check whether method declarations do not contain unnecessary whitespace
between method name and the opening parenthesis for parameter list.

Examples of violations:

        class ClassWithWhitespaceInConstructorDeclaration {
            
            ClassWithWhitespaceInConstructorDeclaration () { //violation
            }
            
            void methodWithWhitespaceInDeclaration () { //violation
            }
        }

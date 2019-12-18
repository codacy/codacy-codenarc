
*Since CodeNarc 0.13*

String objects should be created with single quotes, and GString objects created with double quotes.
Creating normal String objects with double quotes is confusing to readers.

Example of violations:

```
    def a = "I am a string"     // violation

    // violation
    def b = """
        I am a string
    """

    def c = "I am a ' string"       // OK

    def d = """I am a ' string"""   // OK

    def e = """I am a ' string"""   // OK

    def f = "I am a \$ string"  // OK

    // OK
    def g = """
        I am a \$ string
    """

    // OK
    def h = """
        I am a $string
    """

    def i = 'i am a string'
    def j = '''i am a
        string
    '''
```



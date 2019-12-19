
*Since CodeNarc 0.18*

Check for `throws` clauses on JUnit test methods. That is not necessary in Groovy.

This rule sets the default value of the *applyToClassNames* property to only match class names
ending in 'Spec', 'Test', 'Tests' or 'TestCase'.

Example of violations:

```
    @Test
    void shouldDoStuff() throws Exception { }           // violation

    @BeforeClass void initialize() throws Exception { } // violation
    @Before void setUp() throws RuntimeException { }    // violation
    @After void tearDown() throws Exception { }         // violation
    @AfterClass void cleanUp() throws Exception { }     // violation
    @Ignore void ignored() throws Exception { }         // violation

    class MyTest extends GroovyTestCase {
        void test1() throws Exception { }               // violation
        public void test2() throws IOException { }      // violation
    }

```



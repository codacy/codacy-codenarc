*Since CodeNarc 0.14*

Reports usages of `java.util.Random`, which can produce very predictable
results. If two instances of Random are created with the same seed and
sequence of method calls, they will generate the exact same results. Use
`java.security.SecureRandom` instead, which provides a cryptographically
strong random number generator. SecureRandom uses PRNG, which means they
are using a deterministic algorithm to produce a pseudo-random number
from a true random seed. SecureRandom produces non-deterministic output.

By default, this rule ignores test classes are ignored.

For more information see:
<http://www.klocwork.com/products/documentation/current/Checkers:SV.RANDOM>

Example of violations:

         def r1 = new Random()
         def r2 = new java.util.Random()
         Math.random()
         java.lang.Math.random()

         // this is OK
         new java.security.SecureRandom()
         new SecureRandom()

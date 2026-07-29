*Since CodeNarc 0.14*

The File.createTempFile() method is insecure, and has been deprecated by
the ESAPI secure coding library. It has been replaced by the ESAPI
Randomizer.getRandomFilename(String) method.

For more information see the ESAPI website:
http://code.google.com/p/owasp-esapi-java/ and the Randomizer Javadoc:
<https://javadoc.io/doc/org.owasp.esapi/esapi/2.2.0.0/org/owasp/esapi/Randomizer.html>


*Since CodeNarc 0.14*

The permissions classes such as `java.security.Permission` and `java.security.BasicPermission` are designed to be extended.
Classes that derive from these permissions classes, however, must prohibit extension. This prohibition ensures that
malicious subclasses cannot change the properties of the derived class. Classes that implement sensitive interfaces
such as `java.security.PrivilegedAction` and `java.security.PrivilegedActionException` must also be declared `final` for analogous reasons.

For more information see: <https://www.securecoding.cert.org/confluence/display/java/SEC07-J.+Classes+that+derive+from+a+sensitive+class+or+implement+a+sensitive+interface+must+be+declared+final>

Example of violations:

```
    class MyPermission extends java.security.Permission {
        MyPermission(String name) { super(name) }
        boolean implies(Permission permission) { true }
        boolean equals(Object obj) { true }
        int hashCode() { 0 }
        String getActions() { "action" }
    }

    class MyBasicPermission extends BasicPermission {
        MyBasicPermission(String name) { super(name) }
    }

    class MyPrivilegedAction implements PrivilegedAction {
        Object run() { 0 }
    }

    class MyPrivilegedActionException extends PrivilegedActionException {
        MyPrivilegedActionException(Exception exception) { super(exception) }
    }
```



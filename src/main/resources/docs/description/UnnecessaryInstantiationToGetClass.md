*Since in CodeNarc 0.12*

Avoid instantiating an object just to call getClass() on it; use the
.class public member instead.

        public class Foo {
         // Replace this
         Class c = new String().getClass();

         // with this:
         Class c = String.class;
        }


*Since CodeNarc 0.12*

An interface should be used only to model a behaviour of a class: using an interface as a container of constants is
a poor usage pattern. Example:

```
    public interface ConstantsInterface {
        public static final int CONSTANT_1 = 0
        public static final String CONSTANT_2 = "1"
    }
```

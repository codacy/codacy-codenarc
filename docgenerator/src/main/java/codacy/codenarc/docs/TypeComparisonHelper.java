package codacy.codenarc.docs;

public class TypeComparisonHelper {
    public static <A> boolean isInt(Class<A> aClass) {
        return aClass == int.class;
    }
}

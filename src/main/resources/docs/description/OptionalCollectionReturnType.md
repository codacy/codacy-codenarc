*Since CodeNarc 2.0.0*

Do not declare a method return type of `Optional<List>` (or
`Collection`, `ArrayList`, `Set`, `Map`, `HashMap`, etc.). Return an
empty collection instead. See [The Java Optional class: 11 more recipes
for preventing null pointer
exceptions](https://blogs.oracle.com/javamagazine/the-java-optional-class-11-more-recipes-for-preventing-null-pointer-exceptions).

This rule checks for `Optional<collection-type>` return types, where
*collection-type* is one of these common collection interfaces or
implementation classes: - `Collection` - `List` (and `ArrayList`,
`LinkedList`) - `Set` (and `HashSet`, `LinkedHashSet`, `EnumSet`) -
`SortedSet` (and `TreeSet`) - `Map` (and `HashMap`, `LinkedHashMap`,
`EnumMap`) - `SortedMap` (and `TreeMap`)

Example of violations:

        class MyClass {
            Optional<Collection<Object>> getCollection() { }        // violation

            private Optional<List> getList() { }                    // violation
            Optional<ArrayList<String>> getArrayList() { }          // violation
            
            protected Optional<Set<BigDecimal>> getSet() { }        // violation
            Optional<HashSet<Boolean>> getHashSet() { }             // violation

            Optional<Map<Integer, String>> getMap() { }             // violation
            Optional<TreeMap<String, String>> getTreeMap() { }      // violation
        }

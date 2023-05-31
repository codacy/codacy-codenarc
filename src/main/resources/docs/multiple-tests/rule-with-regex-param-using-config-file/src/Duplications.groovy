package magic

// from the rule docs.
class Duplications {
    def var1 = [1, null, Boolean.FALSE, 'x', true]
    def var2 = [1, null, Boolean.FALSE, 'x', true]        // violation

    def var1 = [1, [3, 4]]
    def var2 = [1, [3,4]]     // violation

    def var1 = [123, [3, 4, [x:99], 5]]
    def var2 = [99, [3, 4, [x:99], 5]]        // violation [3, 4, [x:99], 5]

    void test() {
        var test = "stuff";
    }
}


Checks for empty *switch* statements. Empty *switch* statements are confusing and serve no purpose.

Here is an example of code that produces a violation:

```
    def myMethod() {
        switch(myVariable) {
            // empty
        }
    }
```



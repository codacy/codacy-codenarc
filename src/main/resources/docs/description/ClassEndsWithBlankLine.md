
*Since CodeNarc 1.3*

Check whether the class ends with a blank line. By default, it enforces that there must be a blank line before
the closing class brace, except:

  * If the class is synthetic (generated)
  * If the class is empty and is written in a single line
  * If the class is a Script class

A blank line is defined as any line that does not contain any visible characters.

This rule can be configured with the following properties:

| Property                    | Description            | Default Value    |
|-----------------------------|------------------------|------------------|
| ignoreSingleLineClasses     | a boolean property to forbid single line classes.If it is false,then single line classes are considered a violation. | `true` |
| ignoreInnerClasses          | A boolean property to ignore inner classes. If it is *false* then inner classes can cause violations. | `false` |
| blankLineRequired           | a boolean property to define if there may be a blank line before the closing class brace. If it is false, the last line before the brace must not be blank. Otherwise, it must be blank. | `true` |

Example of violations:

If *ignoreSingleLineClasses* is `true` and *blankLineRequired* is `true`

```
            class Foo {
                int a

                void hi() {
                }
            }
```

If *ignoreSingleLineClasses* is `false` and *blankLineRequired* is `true`

```
            class Foo extends Bar*String* { }
```


If *ignoreSingleLineClasses* is `true` and *blankLineRequired* is `false`

```
            class Foo {
                int a

                void hi() {
                }

            }
```

If *ignoreSingleLineClasses* is `false` and *blankLineRequired* is `false`

```
            class Foo {
                int a

                void hi() {
                }

            }
```


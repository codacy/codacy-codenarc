
*Since CodeNarc 1.3*

Check whether the class starts with a blank line. By default, it enforces that there must be a blank line after
the opening class brace, except:

  * If the class is synthetic (generated)

  * If the class is empty and is written in a single line

  * If the class is a Script class

A blank line is defined as any line that does not contain any visible characters.

This rule can be configured with the following properties:

| Property                    | Description            | Default Value    |
|-----------------------------|------------------------|------------------|
| ignoreSingleLineClasses     | A boolean property to ignore single line classes. If it is false, then single line classes are considered a violation. | `true` |
| ignoreInnerClasses          | A boolean property to ignore inner classes. If it is *false* then inner classes can cause violations. | `false` |
| blankLineRequired           | A boolean property to define if there may be a blank line after the opening class brace. If it is false, the first content after the brace must not be a blank line. Otherwise, it must be a blank line.                                       | `true` |

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

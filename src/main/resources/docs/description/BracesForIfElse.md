
*Since CodeNarc 0.15*

Checks the location of the opening brace (\{) for if statements. By default, requires them on the same
line, but the `sameLine` property can be set to false to override this.

| Property                    | Description            | Default Value    |
|-----------------------------|------------------------|------------------|
| sameLine                    | If `true`, then the opening brace (\{) for if statement should be on the same line. |  `true` |
| validateElse                | To enable else checking, set the property to `true` | `false`            |
| elseOnSameLineAsClosingBrace| If `true`, then the else statement should be on the same line the same as sameLine as closing brace (\}) | the same as *sameline* |
| elseOnSameLineAsOpeningBrace| If `true`, then the else statement should be on the same line the same as sameLine as opening brace (\{) | the same as *sameline* |

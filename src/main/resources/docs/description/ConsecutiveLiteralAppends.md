
*Since CodeNarc 0.13*

Violations occur when method calls to append(Object) are chained together with literals as parameters. The
chained calls can be joined into one invocation.

Example of violations:

```
    writer.append('foo').append('bar')      // strings can be joined
    writer.append('foo').append(5)          // string and number can be joined
    writer.append('Hello').append("$World") // GString can be joined
```

Example of passing code:

```
    // usage not chained invocation
    writer.append('Hello')
    writer.append('World')

    writer.append(null).append(5)           // nulls cannot be joined

    writer.append().append('Hello')             // no arg append is unknown
    writer.append('a', 'b').append('Hello')     // two arg append is unknown
```



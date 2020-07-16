Verifies that the name of each variable matches a regular expression. By
default it checks that non-`final` variable names start with a lowercase
letter and contains only letters or numbers.

NOTE: The default naming pattern for `final` variable names is
*currently* that they start with an uppercase letter and contain only
uppercase letters, numbers and underscores (i.e., like *constants*). But
our consensus is that they *should* rather be named like regular
variables. See

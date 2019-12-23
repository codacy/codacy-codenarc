
*Since CodeNarc 0.12*

Logger objects should be declared private, static and final.

This rule has a property: `allowProtectedLogger`, which defaults to false. Set it to true if you believe
subclasses should have access to a Logger in a parent class and that Logger should be declared protected or public.

This rule has a property: `allowNonStaticLogger`, which defaults to false. Set it to true if you believe
a logger should be allowed to be non-static.


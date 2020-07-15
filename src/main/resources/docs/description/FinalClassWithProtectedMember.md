*Since CodeNarc 0.12*

This rule finds classes marked final that contain `protected` members.
If a class is `final` then it may not be subclassed, and there is
therefore no point in having a member with `protected` visibility.
Either the class should not be `final` or the member should be private
or protected.

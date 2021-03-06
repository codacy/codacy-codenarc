*Since CodeNarc 0.14*

`SimpleDateFormat` objects should not be used as `static` fields.
SimpleDateFormats are inherently unsafe for multithreaded use. Sharing a
single instance across thread boundaries without proper synchronization
will result in erratic behavior of the application. Under 1.4 problems
seem to surface less often than under Java 5 where you will probably see
random `ArrayIndexOutOfBoundsException` or `IndexOutOfBoundsException`
in `sun.util.calendar.BaseCalendar.getCalendarDateFromFixedDate()`. You
may also experience serialization problems. Using an instance field or a
`ThreadLocal` is recommended.

For more information on this see Sun Bug

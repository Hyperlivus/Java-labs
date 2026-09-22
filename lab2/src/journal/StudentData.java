package journal;

import input.ConsoleSource;
import scheme.SchemeField;

public record StudentData (
    @SchemeField(regexp = "^[А-ЩЬЮЯІЇЄҐ][а-щьюяіїєґ'-]{1,29}$")
    @ConsoleSource(description = "Enter firstname: ")
    String firstname,

    @SchemeField(regexp = "^[А-ЩЬЮЯІЇЄҐ][а-щьюяіїєґ'-]{1,29}$")
    @ConsoleSource(description = "Enter lastname: ")
    String lastname,

    @SchemeField(
       regexp = "^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[0-2])\\.\\d{4}$"
    )
    @ConsoleSource(description = "Enter birthdate: ")
    String birthdate,

    @SchemeField(regexp = "^\\+?\\d{1,3}?[\\s\\-\\(]?\\d{1,4}\\)?[\\s\\-\\d]{5,10}$")
    @ConsoleSource(description = "Enter phone: ")
    String phone,

    @ConsoleSource(description = "Enter address: ")
    String address
)
{}


package org.dmit3ii.todolist.model;

public enum TypeOfHoliday {
    PUBLIC("Public"),
    BANK("Bank"),
    SCHOOL("School"),
    AUTHORITIES("Authorities"),
    OPTIONAL("Optinal"),
    OBSERVANCE("Odservance");

    private final String value;

    TypeOfHoliday(String value) {
        this.value = value;
    }
}

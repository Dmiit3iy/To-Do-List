package org.dmit3ii.todolist.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
public class Holiday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate date;

    private String localName;

    private String name;

    private String countryCode;

    private Boolean fixed;

    private Boolean global;

    private String counties;

    private Integer launchYear;

    private List<TypeOfHoliday> types = new ArrayList<>();
}

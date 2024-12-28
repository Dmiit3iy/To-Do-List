package org.dmit3ii.todolist.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HolidayType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeOfHoliday type;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "holidays_types",
            joinColumns = @JoinColumn(name = "holiday_type_id"),
            inverseJoinColumns = @JoinColumn(name = "holiday_id"))
    Set<Holiday> holidays = new HashSet<>();
}
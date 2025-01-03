package org.dmit3ii.todolist.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NonNull
    private String title;
    @NonNull
    private String description;
    private LocalDate creationDate = LocalDate.now();
    private boolean completed = false;
    @NonNull
    private LocalDate deadline;
}
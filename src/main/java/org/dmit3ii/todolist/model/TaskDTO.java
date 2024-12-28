package org.dmit3ii.todolist.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import java.time.LocalDate;


@Data
@AllArgsConstructor
public class TaskDTO {

    @NonNull
    private String title;
    @NonNull
    private String description;
    @NonNull
    private LocalDate deadline;
}
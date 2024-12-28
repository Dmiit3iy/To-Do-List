package org.dmit3ii.todolist.service;

import org.dmit3ii.todolist.model.Task;
import org.dmit3ii.todolist.model.TaskDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {
    void addTask(TaskDTO taskDTO);
    Task getById(long id);

    Page<TaskDTO> getAllTasks(Pageable pageable);

    TaskDTO deleteTasById(long id);

    TaskDTO updateTask(TaskDTO taskDTO);
}

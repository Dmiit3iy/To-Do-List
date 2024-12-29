package org.dmit3ii.todolist.service;

import org.dmit3ii.todolist.model.Task;
import org.dmit3ii.todolist.model.TaskDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface TaskService {
    Task addTask(TaskDTO taskDTO);

    Task getById(long id);

    Page<Task> getAllTasks(Pageable pageable);

    Task deleteTasById(long id);

    Task updateTask(Task task);
}

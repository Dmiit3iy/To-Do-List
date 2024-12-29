package org.dmit3ii.todolist.controller;

import lombok.AllArgsConstructor;
import org.dmit3ii.todolist.model.Task;
import org.dmit3ii.todolist.model.TaskDTO;
import org.dmit3ii.todolist.model.TaskMapper;
import org.dmit3ii.todolist.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
@AllArgsConstructor
public class TaskController {
    TaskService taskService;
    private final TaskMapper taskMapper;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskDTO taskDTO) {
        Task task = taskService.addTask(taskDTO);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<TaskDTO>> getAllTasks(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size,
                                                     @RequestParam(defaultValue = "completed") String sortBy,
                                                     @RequestParam(defaultValue = "asc") String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return new ResponseEntity<>(taskService.getAllTasks(pageable).map(taskMapper::taskToTaskDTO), HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<Task> updateTask(@RequestBody Task task) {
        return new ResponseEntity<>(taskService.updateTask(task), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable Long id) {
        return new ResponseEntity<>(taskService.deleteTasById(id), HttpStatus.OK);
    }

}

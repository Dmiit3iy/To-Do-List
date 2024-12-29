package org.dmit3ii.todolist.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dmit3ii.todolist.model.Task;
import org.dmit3ii.todolist.model.TaskDTO;
import org.dmit3ii.todolist.model.TaskMapper;
import org.dmit3ii.todolist.service.TaskService;
import org.dmit3ii.todolist.service.ValidationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j

@RestController
@RequestMapping("/api/v1/tasks")
@AllArgsConstructor
public class TaskController {
    TaskService taskService;
    ValidationService validationService;
    private final TaskMapper taskMapper;

    @Operation(summary = "Создать задачу",
            description = "Создает задачу согласно переданному DTO"
    )
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskDTO taskDTO, @RequestParam(defaultValue = "RU") String countryCode) {
        validationService.validateDay(taskDTO.getDeadline(), countryCode);
        Task task = taskService.addTask(taskDTO);
        log.info("Task created: {}", task);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @Operation(summary = "Получить список задач",
            description = "Получение списка всех задач. Возможность фильтрации по статусу задачи (completed: “true”, “false”)"
    )
    @GetMapping
    public ResponseEntity<Page<TaskDTO>> getAllTasks(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size,
                                                     @RequestParam(defaultValue = "completed") String sortBy,
                                                     @RequestParam(defaultValue = "asc") String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return new ResponseEntity<>(taskService.getAllTasks(pageable).map(taskMapper::taskToTaskDTO), HttpStatus.OK);

    }

    @Operation(summary = "Изменить задачу",
            description = "Обновление существующей задачи. Можно обновить заголовок, описание и статус задачи в " +
                    "зависимости от переданного нового объекта")
    @PutMapping
    public ResponseEntity<Task> updateTask(@RequestBody Task task) {
        Task updatedTask = taskService.updateTask(task);
        log.info("Task updated: {}", updatedTask);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @Operation(summary = "Удалить задачу по id",
            description = "Удаление задачи по ее id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable Long id) {
        Task deletedTask = taskService.deleteTasById(id);
        log.info("Task deleted: {}", deletedTask);
        return new ResponseEntity<>(deletedTask, HttpStatus.OK);
    }

}

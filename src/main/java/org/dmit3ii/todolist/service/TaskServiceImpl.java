package org.dmit3ii.todolist.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.dmit3ii.todolist.model.Task;
import org.dmit3ii.todolist.model.TaskDTO;
import org.dmit3ii.todolist.model.TaskMapper;
import org.dmit3ii.todolist.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;
    private TaskMapper taskMapper;

    @Override
    public Task addTask(TaskDTO taskDTO) {
        return taskRepository.save(taskMapper.taskDTOToTask(taskDTO));
    }

    @Override
    public Task getById(long id) {
        return taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Задачи с таким ID нет!"));
    }

    @Override
    public Page<Task> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    @Override
    public Task deleteTasById(long id) {
        Task task = getById(id);
        taskRepository.deleteById(task.getId());
        return task;
    }

    @Override
    public Task updateTask(Task task) {
        Task baseTask = getById(task.getId());
        baseTask.setTitle(task.getTitle());
        baseTask.setDescription(task.getDescription());
        baseTask.setDeadline(task.getDeadline());
        return taskRepository.save(baseTask);
    }
}

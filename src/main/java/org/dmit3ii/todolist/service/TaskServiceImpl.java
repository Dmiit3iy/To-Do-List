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
    public void addTask(TaskDTO taskDTO) {
        taskRepository.save(taskMapper.taskDTOToTask(taskDTO));
    }

    @Override
    public Task getById(long id) {
        return taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Задачи с таким ID нет!"));
    }

    @Override
    public Page<TaskDTO> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable).map(taskMapper::taskToTaskDTO);
    }

    @Override
    public TaskDTO deleteTasById(long id) {
        Task task = getById(id);
        taskRepository.deleteById(task.getId());
        return taskMapper.taskToTaskDTO(task);
    }

    @Override
    public TaskDTO updateTask(TaskDTO taskDTO) {
        return null;
    }
}

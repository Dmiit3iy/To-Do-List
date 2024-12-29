package org.dmit3ii.todolist.model;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper (componentModel = "spring")
public interface TaskMapper {

    TaskDTO taskToTaskDTO(Task task);

    Task taskDTOToTask(TaskDTO taskDTO);

    List<TaskDTO> tasksListToTasksDtoList(List<Task> tasksList);
}

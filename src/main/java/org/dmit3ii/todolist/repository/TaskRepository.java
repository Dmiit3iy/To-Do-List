package org.dmit3ii.todolist.repository;

import org.dmit3ii.todolist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}

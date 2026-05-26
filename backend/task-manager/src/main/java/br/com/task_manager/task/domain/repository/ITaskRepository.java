package br.com.task_manager.task.domain.repository;

import br.com.task_manager.task.domain.entity.TaskEntity;
import java.util.List;
import java.util.Optional;

public interface ITaskRepository {
    List<TaskEntity> getAllTasks();
    List<TaskEntity> findByUserId(Long userId);
    TaskEntity addNewTask(TaskEntity entity);
    void update(TaskEntity entity);
    void deleteById(long id);
    Optional<TaskEntity> findTaskById(Long id);
}
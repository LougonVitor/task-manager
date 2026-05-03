package br.com.task_manager.task.domain.repository;

import br.com.task_manager.task.domain.entity.TaskEntity;
import java.util.List;

public interface ITaskRepository {
    List<TaskEntity> getAllTasks();
    List<TaskEntity> findByUserId(Long userId);
    TaskEntity addNewTask(TaskEntity entity);
    void updateData(long id, TaskEntity entity);
    void deleteById(long id);
    TaskEntity findById(long id);
}
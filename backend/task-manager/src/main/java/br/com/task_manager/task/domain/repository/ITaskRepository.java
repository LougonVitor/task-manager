package br.com.task_manager.task.domain.repository;

import br.com.task_manager.task.domain.entity.TaskEntity;
import java.util.List;

public interface ITaskRepository {
    List<TaskEntity> getAllTasks();
    TaskEntity createTask(TaskEntity entity);
}
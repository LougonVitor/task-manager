package br.com.task_manager.task.domain.repository;

import br.com.task_manager.task.domain.entity.TaskEntity;

public interface ITaskRepository {
    Long createTask(TaskEntity entity);
}
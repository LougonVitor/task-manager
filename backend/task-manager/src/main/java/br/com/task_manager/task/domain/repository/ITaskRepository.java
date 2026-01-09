package br.com.task_manager.task.domain.repository;

import br.com.task_manager.task.domain.entity.TaskEntity;

public interface ITaskRepository {
    TaskEntity createTask(TaskEntity entity);
}
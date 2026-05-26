package br.com.task_manager.task.application.service;

import br.com.task_manager.task.application.dto.UpdateTaskCommand;
import br.com.task_manager.task.domain.entity.TaskEntity;
import br.com.task_manager.task.domain.exception.ResourceNotFoundException;
import br.com.task_manager.task.domain.repository.ITaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskUpdateService {
    private final ITaskRepository taskRepository;

    public TaskUpdateService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void updateData(long taskId, UpdateTaskCommand command, Long currentUserId) {
        TaskEntity entity = this.fetchTaskData(taskId);

        entity.validateOwnership(currentUserId);
        entity.updateTaskData(command.title(), command.description(), command.deadline());

        this.update(entity);
    }

    public void toggleStatus(long taskId, Long currentUserId) {
        TaskEntity entity = this.fetchTaskData(taskId);

        entity.validateOwnership(currentUserId);
        entity.toggleStatus();

        this.update(entity);
    }

    private TaskEntity fetchTaskData(Long taskId) {
        return this.taskRepository.findTaskById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task not found by id!"));
    }

    private void update(TaskEntity entity) {
        this.taskRepository.update(entity);
    }
}
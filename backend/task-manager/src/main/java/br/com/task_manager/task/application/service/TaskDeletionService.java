package br.com.task_manager.task.application.service;

import br.com.task_manager.task.domain.entity.TaskEntity;
import br.com.task_manager.task.domain.exception.ResourceNotFoundException;
import br.com.task_manager.task.domain.repository.ITaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskDeletionService {
    private final ITaskRepository taskRepository;

    public TaskDeletionService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void deleteById(long taskId, Long currentUserId) {
        TaskEntity entity = this.taskRepository.findTaskById(taskId).orElseThrow(
            () -> new ResourceNotFoundException("Task not found by id!")
        );

        entity.validateOwnership(currentUserId);

        this.taskRepository.deleteById(entity.getId());
    }
}
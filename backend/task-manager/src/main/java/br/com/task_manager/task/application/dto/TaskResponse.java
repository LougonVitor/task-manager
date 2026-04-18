package br.com.task_manager.task.application.dto;

import br.com.task_manager.task.domain.entity.TaskEntity;
import br.com.task_manager.task.domain.valueobject.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TaskResponse(Long id, String title, String description, TaskStatus taskStatus, LocalDateTime createdAt, LocalDate deadline, LocalDateTime completedAt) {
    public TaskResponse(TaskEntity entity) {
        this(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getTaskStatus(),
                entity.getCreatedAt(),
                entity.getDeadline(),
                entity.getCompletedAt()
        );
    }
}
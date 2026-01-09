package br.com.task_manager.task.domain.entity;

import br.com.task_manager.task.domain.valueobject.TaskStatus;

import java.time.LocalDateTime;

public class TaskEntity {
    private String title;
    private String description;
    private TaskStatus taskStatus;
    private LocalDateTime createdAt;
    private LocalDateTime deadline;
    private LocalDateTime completedAt;
}
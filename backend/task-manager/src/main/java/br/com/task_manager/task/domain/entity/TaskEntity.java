package br.com.task_manager.task.domain.entity;

import br.com.task_manager.task.domain.exception.AccessDeniedException;
import br.com.task_manager.task.domain.valueobject.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TaskEntity {
    private Long id;
    private String title;
    private String description;
    private TaskStatus taskStatus;
    private LocalDateTime createdAt;
    private LocalDate deadline;
    private LocalDateTime completedAt;
    private Long userId;

    //All args constructor
    public TaskEntity (Long id, String title, String description, TaskStatus taskStatus, LocalDateTime createdAt, LocalDate deadline, LocalDateTime completedAt, Long userId) {
        this.setId(id);
        this.setTitle(title);
        this.setDescription(description);
        this.setTaskStatus(taskStatus);
        this.setCreatedAt(createdAt);
        this.setDeadline(deadline);
        this.setCompletedAt(completedAt);
        this.setUserId(userId);
    }

    //Creation constructor
    public TaskEntity (String title, String description, String taskStatus, LocalDateTime createdAt, LocalDate deadline, Long userId) {
        this.setTitle(title);
        this.setDescription(description);
        this.setTaskStatus(TaskStatus.getEnumValue(taskStatus));
        this.setCreatedAt(createdAt);
        this.setDeadline(deadline);
        this.setUserId(userId);
    }

    public void updateTaskData(String title, String description, LocalDate deadline) {
        this.setTitle(title);
        this.setDescription(description);
        this.setDeadline(deadline);
    }

    public void toggleStatus() {
        this.setTaskStatus(this.getTaskStatus() == TaskStatus.COMPLETED
                ? TaskStatus.IN_PROGRESS
                : TaskStatus.COMPLETED
        );
    }

    public void validateOwnership(Long currentUserId) {
        if(!this.getUserId().equals(currentUserId)) throw new AccessDeniedException("The logged-in user does not have access to this task.");
    }




    //Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
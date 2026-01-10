package br.com.task_manager.task.application.dto;

import java.time.LocalDateTime;

public record CreateTaskCommand(String title, String description, String status, LocalDateTime deadline) {
}
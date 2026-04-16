package br.com.task_manager.task.application.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CreateTaskCommand(String title, String description, String status, LocalDate deadline) {
}
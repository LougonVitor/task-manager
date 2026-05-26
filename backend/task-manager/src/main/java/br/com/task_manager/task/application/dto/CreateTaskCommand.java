package br.com.task_manager.task.application.dto;

import java.time.LocalDate;

public record CreateTaskCommand(String title, String description, String status, LocalDate deadline) {
}
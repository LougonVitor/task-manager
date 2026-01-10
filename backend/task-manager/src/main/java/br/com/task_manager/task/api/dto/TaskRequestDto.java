package br.com.task_manager.task.api.dto;

import java.time.LocalDateTime;

public record TaskRequestDto(String title, String description, String status, LocalDateTime deadline) {
}
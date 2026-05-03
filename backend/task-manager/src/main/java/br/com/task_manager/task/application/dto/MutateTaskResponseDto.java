package br.com.task_manager.task.application.dto;

import java.time.LocalDateTime;

public record MutateTaskResponseDto(Long id, String title, LocalDateTime createdAt) {
}
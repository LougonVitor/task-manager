package br.com.task_manager.task.api.dto;

import java.time.LocalDateTime;

public record TaskResponseDto(Long id, String Title, LocalDateTime createdAt){}
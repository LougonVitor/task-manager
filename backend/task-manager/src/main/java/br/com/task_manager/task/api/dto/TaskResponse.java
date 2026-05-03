package br.com.task_manager.task.api.dto;

import java.time.LocalDateTime;

public record TaskResponse(Long id, String title, LocalDateTime createdAt){}
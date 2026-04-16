package br.com.task_manager.task.api.dto;

import java.time.LocalDate;

public record TaskRequestDto(String title, String description, String status, LocalDate deadline) {
}
package br.com.task_manager.task.api.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskRequestDto(
    
    @NotBlank(message = "Title cannot be null.")
    String title
    
    , 
    @NotBlank(message = "Description cannot be null.")
    String description
    
    , 
    @NotBlank(message = "Status field must have a value.")
    String status
    
    , 
    @NotNull(message = "All tasks must have a deadline.")
    LocalDate deadline
    
    , Long userId) {
}
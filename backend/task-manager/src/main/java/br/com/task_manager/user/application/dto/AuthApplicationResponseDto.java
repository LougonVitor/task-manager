package br.com.task_manager.user.application.dto;

public record AuthApplicationResponseDto(String token) {
    public AuthApplicationResponseDto(String token) {
        this.token = token;
    }
}
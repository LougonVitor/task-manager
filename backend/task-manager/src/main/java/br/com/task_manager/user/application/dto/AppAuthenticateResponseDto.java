package br.com.task_manager.user.application.dto;

public record AppAuthenticateResponseDto(String token) {
    public AppAuthenticateResponseDto(String token) {
        this.token = token;
    }
}
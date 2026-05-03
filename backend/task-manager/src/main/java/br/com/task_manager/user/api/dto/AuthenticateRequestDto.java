package br.com.task_manager.user.api.dto;

public record AuthenticateRequestDto(String username, String password) {
}
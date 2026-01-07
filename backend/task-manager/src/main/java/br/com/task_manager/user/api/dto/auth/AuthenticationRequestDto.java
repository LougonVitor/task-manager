package br.com.task_manager.user.api.dto.auth;

public record AuthenticationRequestDto(String username, String password) {
}
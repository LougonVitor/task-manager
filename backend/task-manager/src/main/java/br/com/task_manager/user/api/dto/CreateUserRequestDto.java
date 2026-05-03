package br.com.task_manager.user.api.dto;

public record CreateUserRequestDto(String username, String email, String password, String role) {
}
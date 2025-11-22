package br.com.task_manager.user.api.dto;

public record UserRequestDto(String username, String email, String password) {
}
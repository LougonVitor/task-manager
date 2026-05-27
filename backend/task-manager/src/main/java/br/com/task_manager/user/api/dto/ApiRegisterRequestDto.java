package br.com.task_manager.user.api.dto;

public record ApiRegisterRequestDto(String username, String email, String password, String role) {
}
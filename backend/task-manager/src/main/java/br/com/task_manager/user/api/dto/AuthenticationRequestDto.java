package br.com.task_manager.user.api.dto;

public record AuthenticationRequestDto(String username, String password) {
}
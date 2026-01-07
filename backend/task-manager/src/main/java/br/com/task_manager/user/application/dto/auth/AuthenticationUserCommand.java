package br.com.task_manager.user.application.dto.auth;

public record AuthenticationUserCommand(String username, String password) {
}
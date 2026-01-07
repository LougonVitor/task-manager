package br.com.task_manager.user.application.dto.auth;

public record CreateUserCommand (String username, String email, String password, String role) {}
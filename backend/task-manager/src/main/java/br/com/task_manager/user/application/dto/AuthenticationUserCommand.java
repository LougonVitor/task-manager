package br.com.task_manager.user.application.dto;

public record AuthenticationUserCommand(String username, String password) {
}
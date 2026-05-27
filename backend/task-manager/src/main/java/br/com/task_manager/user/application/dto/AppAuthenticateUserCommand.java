package br.com.task_manager.user.application.dto;

public record AppAuthenticateUserCommand(String username, String password) {
}
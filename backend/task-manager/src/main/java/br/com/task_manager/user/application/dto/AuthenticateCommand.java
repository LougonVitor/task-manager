package br.com.task_manager.user.application.dto;

public record AuthenticateCommand(String username, String password) {
}
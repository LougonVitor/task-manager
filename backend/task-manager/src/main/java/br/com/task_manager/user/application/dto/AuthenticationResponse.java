package br.com.task_manager.user.application.dto;

public record AuthenticationResponse (String token) {
    public AuthenticationResponse (String token) {
        this.token = token;
    }
}
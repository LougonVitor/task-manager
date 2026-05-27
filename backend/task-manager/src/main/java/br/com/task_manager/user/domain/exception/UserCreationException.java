package br.com.task_manager.user.domain.exception;

public class UserCreationException extends RuntimeException {
    public UserCreationException(String username) {
        super("Failed to create user with username: " + username);
    }

    public UserCreationException(String username, Throwable cause) {
        super("Failed to create user with username: " + username, cause);
    }
}
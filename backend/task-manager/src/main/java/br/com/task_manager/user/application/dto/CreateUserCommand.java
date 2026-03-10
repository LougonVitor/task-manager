package br.com.task_manager.user.application.dto;

import br.com.task_manager.user.domain.valueobject.UserRole;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public record CreateUserCommand (String username, String email, String password, String role) {
    public String getEncryptedPassword() {
        return new BCryptPasswordEncoder().encode(password);
    }

    public UserRole getRoleAsEnum() {
        return UserRole.valueOf(role);
    }
}
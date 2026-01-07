package br.com.task_manager.user.api.dto.auth;

import br.com.task_manager.user.domain.valueobject.UserRole;

public record CreateRequestDto(String username, String email, String password, String role) {
}

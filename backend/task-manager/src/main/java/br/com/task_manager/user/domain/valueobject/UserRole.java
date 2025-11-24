package br.com.task_manager.user.domain.valueobject;

public enum UserRole {
    ADMIN("admin"),
    COMMON("common");

    private final String role;

    UserRole (String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

    public static UserRole getEnumValue (String role) {
        return UserRole.valueOf(role.toUpperCase());
    }
}
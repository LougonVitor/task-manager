package br.com.task_manager.user.domain.entity;

import java.time.LocalDateTime;

public class UserEntity {
    private Long id;
    private String username;
    private String email;
    private String password;
    private LocalDateTime createdAt;
}
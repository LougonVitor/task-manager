package br.com.task_manager.user.infrastructure.entity;

import br.com.task_manager.user.domain.entity.UserEntity;
import br.com.task_manager.user.infrastructure.repository.UserJpaAdapter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "users")
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    private String role;
    @Column(name = "dh_creation")
    private LocalDateTime createdAt;

    public UserJpaEntity (UserEntity entity) {
        this.setUsername(entity.getUsername());
        this.setEmail(entity.getEmail());
        this.setPassword(entity.getPassword());
        this.setCreatedAt(LocalDateTime.now());
        this.setRole(entity.getRole().getRole());
    }
}
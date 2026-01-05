package br.com.task_manager.user.infrastructure.repository;

import br.com.task_manager.user.domain.entity.UserEntity;
import br.com.task_manager.user.infrastructure.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {
    UserJpaEntity findByUsername(String username);
}
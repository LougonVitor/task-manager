package br.com.task_manager.user.infrastructure.repository;

import br.com.task_manager.user.infrastructure.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {
}
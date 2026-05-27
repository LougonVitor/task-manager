package br.com.task_manager.user.domain.repository;

import br.com.task_manager.user.domain.entity.UserEntity;

import java.util.Optional;

public interface IUserRepository {
    Optional<UserEntity> create(UserEntity entity);
    Optional<UserEntity> findByUsername(String username);
}
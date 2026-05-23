package br.com.task_manager.user.domain.repository;

import br.com.task_manager.user.domain.entity.UserEntity;

public interface IUserRepository {
    UserEntity create(UserEntity entity);
    UserEntity findByUsername(String username);
}
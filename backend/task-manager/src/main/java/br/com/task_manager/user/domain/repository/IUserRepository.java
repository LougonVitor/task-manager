package br.com.task_manager.user.domain.repository;

import br.com.task_manager.user.domain.entity.UserEntity;
import org.apache.catalina.User;

public interface IUserRepository {
    UserEntity createUser(UserEntity entity);
}
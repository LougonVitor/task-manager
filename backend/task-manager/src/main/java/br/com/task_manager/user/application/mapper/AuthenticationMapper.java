package br.com.task_manager.user.application.mapper;

import br.com.task_manager.user.application.dto.CreateUserCommand;
import br.com.task_manager.user.domain.entity.UserEntity;

public class AuthenticationMapper {
    public static UserEntity toEntity(CreateUserCommand command) {
        return new UserEntity(
                command.username()
                , command.email()
                , command.getEncryptedPassword()
                , command.getRoleAsEnum()
        );
    }
}
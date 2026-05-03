package br.com.task_manager.user.application.mapper;

import br.com.task_manager.user.application.dto.CreateUserCommand;
import br.com.task_manager.user.domain.entity.UserEntity;

public class AuthApplicationMapper {
    /**
     * Converts a {@link CreateUserCommand} from the application layer
     * into a domain-level {@link UserEntity}.
     *
     * @param command the application command containing user registration details
     * @return a fully constructed {@code UserEntity} ready for business validation and persistence
     */
    public static UserEntity toEntity(CreateUserCommand command) {
        return new UserEntity(
                command.username()
                , command.email()
                , command.getEncryptedPassword()
                , command.getRoleAsEnum()
        );
    }
}
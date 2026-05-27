package br.com.task_manager.user.application.mapper;

import br.com.task_manager.user.application.dto.AppRegisterResponseDto;
import br.com.task_manager.user.application.dto.AppRegisterUserCommand;
import br.com.task_manager.user.domain.entity.UserEntity;

public class AppAuthMapper {
    /**
     * Converts a {@link AppRegisterUserCommand} from the application layer
     * into a domain-level {@link UserEntity}.
     *
     * @param command the application command containing user registration details
     * @return a fully constructed {@code UserEntity} ready for business validation and persistence
     */
    public static UserEntity toDomainEntity(AppRegisterUserCommand command) {
        return new UserEntity(
                command.username()
                , command.email()
                , command.getEncryptedPassword()
                , command.getRoleAsEnum()
        );
    }

    /**
     * Converts a {@link UserEntity} from the domain layer
     * into an {@link AppRegisterResponseDto} for the application layer.
     *
     * @param entity the persisted user entity containing registration result data
     * @return an {@code AppRegisterResponseDto} containing the username and creation timestamp
     */
    public static AppRegisterResponseDto toAppRegisterResponseDto(UserEntity entity) {
        return new AppRegisterResponseDto(entity.getUsername(), entity.getCreatedAt().toString());
    }
}
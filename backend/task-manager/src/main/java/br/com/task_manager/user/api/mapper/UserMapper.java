package br.com.task_manager.user.api.mapper;

import br.com.task_manager.user.api.dto.UserRequestDto;
import br.com.task_manager.user.application.dto.auth.CreateUserCommand;
import br.com.task_manager.user.api.dto.UserResponseDto;
import br.com.task_manager.user.application.dto.UserServiceResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public static CreateUserCommand toCreateUserCommand(UserRequestDto request) {
        return new CreateUserCommand(
                request.username(),
                request.email(),
                request.password(),
                request.role()
        );
    }

    public static UserResponseDto toResponseDto(UserServiceResponseDto serviceResponseDto) {
        return new UserResponseDto(serviceResponseDto.id(), serviceResponseDto.username(), serviceResponseDto.email());
    }
}
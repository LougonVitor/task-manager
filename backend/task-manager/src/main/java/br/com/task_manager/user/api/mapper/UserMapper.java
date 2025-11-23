package br.com.task_manager.user.api.mapper;

import br.com.task_manager.user.api.dto.UserRequestDto;
import br.com.task_manager.user.api.dto.UserResponseDto;
import br.com.task_manager.user.domain.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public static UserEntity toDomain(UserRequestDto request) {
        return new UserEntity(
                request.username(),
                request.email(),
                request.password()
        );
    }

    public static UserResponseDto toResponseDto(UserEntity entity) {
        return new UserResponseDto(entity.getId(), entity.getUsername(), entity.getEmail());
    }
}
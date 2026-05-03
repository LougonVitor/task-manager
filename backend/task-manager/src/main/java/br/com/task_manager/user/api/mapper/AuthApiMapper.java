package br.com.task_manager.user.api.mapper;

import br.com.task_manager.user.api.dto.AuthApiResponseDto;
import br.com.task_manager.user.api.dto.AuthenticateRequestDto;
import br.com.task_manager.user.api.dto.CreateUserRequestDto;
import br.com.task_manager.user.application.dto.AuthApplicationResponseDto;
import br.com.task_manager.user.application.dto.CreateUserCommand;
import br.com.task_manager.user.application.dto.AuthenticateCommand;
import org.springframework.stereotype.Component;

@Component
public class AuthApiMapper {
    /**
     * Converts an incoming {@link AuthenticateRequestDto} from the API layer
     * into an {@link AuthenticateCommand} for the application layer.
     *
     * @param requestDto the incoming authentication request data containing credentials
     * @return an {@code AuthenticationUserCommand} ready for processing by the authentication use case
     */
    public static AuthenticateCommand toAuthenticationCommand(AuthenticateRequestDto requestDto) {
        return new AuthenticateCommand(requestDto.username(), requestDto.password());
    }

    /**
     * Converts an incoming {@link CreateUserRequestDto} from the API layer
     * into a {@link CreateUserCommand} for the application layer.
     *
     * @param request the incoming registration request data containing user details
     * @return a {@code CreateUserCommand} containing the details required to register a new user
     */
    public static CreateUserCommand toCreateCommand(CreateUserRequestDto request) {
        return new CreateUserCommand(request.username(), request.email(), request.password(), request.role());
    }

    /**
     * Converts an incoming {@link AuthApplicationResponseDto} from the APPLICATION layer
     * into a {@link AuthApiResponseDto} for the API layer.
     *
     * @param applicationResponseDto the incoming authentication response data containing the USER TOKEN SERVICE
     * @return a {@code AuthApiResponseDto} containing only the USER TOKEN
     */
    public static AuthApiResponseDto toResponse(AuthApplicationResponseDto applicationResponseDto) {
        return new AuthApiResponseDto(applicationResponseDto.token());
    }
}
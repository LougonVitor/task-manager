package br.com.task_manager.user.api.mapper;

import br.com.task_manager.user.api.dto.ApiAuthenticateResponseDto;
import br.com.task_manager.user.api.dto.ApiRegisterResponseDto;
import br.com.task_manager.user.api.dto.ApiAuthenticateRequestDto;
import br.com.task_manager.user.api.dto.ApiRegisterRequestDto;
import br.com.task_manager.user.application.dto.AppRegisterResponseDto;
import br.com.task_manager.user.application.dto.AppAuthenticateResponseDto;
import br.com.task_manager.user.application.dto.AppRegisterUserCommand;
import br.com.task_manager.user.application.dto.AppAuthenticateUserCommand;

public class ApiAuthMapper {
    /**
     * Converts an incoming {@link ApiAuthenticateRequestDto} from the API layer
     * into an {@link AppAuthenticateUserCommand} for the application layer.
     *
     * @param requestDto the incoming authentication request data containing credentials
     * @return an {@code AuthenticationUserCommand} ready for processing by the authentication use case
     */
    public static AppAuthenticateUserCommand toAuthenticateCommand(ApiAuthenticateRequestDto requestDto) {
        return new AppAuthenticateUserCommand(requestDto.username(), requestDto.password());
    }

    /**
     * Converts an incoming {@link ApiRegisterRequestDto} from the API layer
     * into a {@link AppRegisterUserCommand} for the application layer.
     *
     * @param request the incoming registration request data containing user details
     * @return a {@code CreateUserCommand} containing the details required to register a new user
     */
    public static AppRegisterUserCommand toRegisterCommand(ApiRegisterRequestDto request) {
        return new AppRegisterUserCommand(request.username(), request.email(), request.password(), request.role());
    }

    /**
     * Converts an incoming {@link AppAuthenticateResponseDto} from the APPLICATION layer
     * into a {@link ApiAuthenticateResponseDto} for the API layer.
     *
     * @param applicationResponseDto the incoming authentication response data containing the USER TOKEN SERVICE
     * @return a {@code AuthApiResponseDto} containing only the USER TOKEN
     */
    public static ApiAuthenticateResponseDto toAuthenticateApiResponse(AppAuthenticateResponseDto applicationResponseDto) {
        return new ApiAuthenticateResponseDto(applicationResponseDto.token());
    }

    /**
     * Converts an {@link AppRegisterResponseDto} from the application layer
     * into an {@link ApiRegisterResponseDto} for the API layer.
     *
     * @param applicationResponseDto the application response containing the registration result data
     * @return an {@code ApiRegisterResponseDto} containing the username and creation timestamp
     */
    public static ApiRegisterResponseDto toRegisterApiResponse(AppRegisterResponseDto applicationResponseDto) {
        return new ApiRegisterResponseDto(applicationResponseDto.username(), applicationResponseDto.createdAt());
    }
}
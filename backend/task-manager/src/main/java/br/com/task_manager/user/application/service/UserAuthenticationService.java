package br.com.task_manager.user.application.service;

import br.com.task_manager.user.application.dto.AppAuthenticateResponseDto;
import br.com.task_manager.user.application.dto.AppAuthenticateUserCommand;
import br.com.task_manager.common.security.TokenService;
import br.com.task_manager.user.infrastructure.security.UserSecurityDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService {
    private final TokenService tokenService;

    private final AuthenticationManager authenticationManager;

    public UserAuthenticationService(TokenService tokenService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    public AppAuthenticateResponseDto authenticate(AppAuthenticateUserCommand command) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(command.username(), command.password());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        UserSecurityDetails userDetails = (UserSecurityDetails) auth.getPrincipal();

        String token = this.tokenService.generateToken(userDetails, userDetails.getUserId());
        return new AppAuthenticateResponseDto(token);
    }
}
package br.com.task_manager.user.application.service;

import br.com.task_manager.user.application.dto.AuthApplicationResponseDto;
import br.com.task_manager.user.application.dto.AuthenticateCommand;
import br.com.task_manager.common.security.TokenService;
import br.com.task_manager.user.infrastructure.security.UserSecurityDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthApplicationResponseDto authenticate(AuthenticateCommand command) {
        return authenticateCommand(command);
    }

    private AuthApplicationResponseDto authenticateCommand(AuthenticateCommand command) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(command.username(), command.password());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        UserSecurityDetails userDetails = (UserSecurityDetails) auth.getPrincipal();

        String token = this.tokenService.generateToken(userDetails, userDetails.getUserId());
        return new AuthApplicationResponseDto(token);
    }
}
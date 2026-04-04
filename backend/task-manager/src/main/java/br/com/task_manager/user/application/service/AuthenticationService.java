package br.com.task_manager.user.application.service;

import br.com.task_manager.user.application.dto.AuthenticationResponse;
import br.com.task_manager.user.application.dto.AuthenticationUserCommand;
import br.com.task_manager.common.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse loginAuthentication(AuthenticationUserCommand command) {
        return authenticateCommand(command);
    }

    private AuthenticationResponse authenticateCommand(AuthenticationUserCommand command) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(command.username(), command.password());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        String token = this.tokenService.generateToken((User) auth.getPrincipal());
        return new AuthenticationResponse(token);
    }
}
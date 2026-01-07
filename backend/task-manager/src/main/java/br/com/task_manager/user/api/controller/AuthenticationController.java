package br.com.task_manager.user.api.controller;

import br.com.task_manager.user.api.dto.auth.AuthenticationRequestDto;
import br.com.task_manager.user.api.dto.auth.CreateRequestDto;
import br.com.task_manager.user.api.mapper.AuthenticationMapper;
import br.com.task_manager.user.application.dto.auth.AuthenticationUserCommand;
import br.com.task_manager.user.application.dto.auth.CreateUserCommand;
import br.com.task_manager.user.application.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationServiceService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthenticationRequestDto request) {
        AuthenticationUserCommand serviceDto = AuthenticationMapper.toAuthenticationUserCommand(request);
        String token = this.authenticationServiceService.loginAuthentication(serviceDto);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated CreateRequestDto request) throws Exception{
        CreateUserCommand createUserCommand = AuthenticationMapper.toCreateUserCommand(request);
        String username = this.authenticationServiceService.createUser(createUserCommand);
        return ResponseEntity.ok(username);
    }
}
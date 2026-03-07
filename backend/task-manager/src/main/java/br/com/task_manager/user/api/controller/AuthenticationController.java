package br.com.task_manager.user.api.controller;

import br.com.task_manager.user.api.dto.AuthenticationRequestDto;
import br.com.task_manager.user.api.dto.CreateRequestDto;
import br.com.task_manager.user.api.mapper.UserAuthenticationMapper;
import br.com.task_manager.user.application.dto.AuthenticationUserCommand;
import br.com.task_manager.user.application.dto.CreateUserCommand;
import br.com.task_manager.user.application.service.AuthenticationService;
import br.com.task_manager.user.application.service.CreateUserService;
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

    @Autowired
    private CreateUserService createUserService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthenticationRequestDto request) {
        AuthenticationUserCommand serviceDto = UserAuthenticationMapper.toAuthenticationUserCommand(request);
        String token = this.authenticationServiceService.loginAuthentication(serviceDto);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated CreateRequestDto request) throws Exception{
        CreateUserCommand createUserCommand = UserAuthenticationMapper.toCreateUserCommand(request);
        String username = this.createUserService.createUser(createUserCommand);
        return ResponseEntity.ok(username);
    }
}
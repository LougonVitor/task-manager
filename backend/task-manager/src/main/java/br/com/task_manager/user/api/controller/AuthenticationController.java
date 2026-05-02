package br.com.task_manager.user.api.controller;

import br.com.task_manager.user.api.dto.AuthenticationRequestDto;
import br.com.task_manager.user.api.dto.CreateRequestDto;
import br.com.task_manager.user.api.mapper.UserAuthenticationMapper;
import br.com.task_manager.user.application.dto.AuthenticationResponse;
import br.com.task_manager.user.application.service.AuthenticationService;
import br.com.task_manager.user.application.service.CreateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private CreateUserService createUserService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Validated AuthenticationRequestDto request) {
        AuthenticationResponse response = this.authenticationService.loginAuthentication(UserAuthenticationMapper.toAuthenticationUserCommand(request));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Validated CreateRequestDto request) throws Exception{
        String username = this.createUserService.createUser(UserAuthenticationMapper.toCreateUserCommand(request));
        return ResponseEntity.ok(username);
    }
}
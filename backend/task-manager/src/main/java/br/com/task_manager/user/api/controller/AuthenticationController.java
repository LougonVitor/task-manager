package br.com.task_manager.user.api.controller;

import br.com.task_manager.user.api.dto.AuthApiResponseDto;
import br.com.task_manager.user.api.dto.AuthenticateRequestDto;
import br.com.task_manager.user.api.dto.CreateUserRequestDto;
import br.com.task_manager.user.api.mapper.AuthApiMapper;
import br.com.task_manager.user.application.service.UserAuthenticationService;
import br.com.task_manager.user.application.service.UserCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private UserAuthenticationService userAuthenticationService;

    @Autowired
    private UserCreationService userCreationService;

    @PostMapping("/login")
    public ResponseEntity<AuthApiResponseDto> login(@RequestBody @Validated AuthenticateRequestDto request) {
        AuthApiResponseDto response = AuthApiMapper.toResponse(
            this.userAuthenticationService.authenticate(AuthApiMapper.toAuthenticationCommand(request))
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Validated CreateUserRequestDto request) throws Exception{
        String username = this.userCreationService.create(AuthApiMapper.toCreateCommand(request));
        return ResponseEntity.ok(username);
    }
}
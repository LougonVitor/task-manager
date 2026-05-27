package br.com.task_manager.user.api.controller;

import br.com.task_manager.user.api.dto.ApiRegisterResponseDto;
import br.com.task_manager.user.api.dto.ApiAuthenticateResponseDto;
import br.com.task_manager.user.api.dto.ApiAuthenticateRequestDto;
import br.com.task_manager.user.api.dto.ApiRegisterRequestDto;
import br.com.task_manager.user.api.mapper.ApiAuthMapper;
import br.com.task_manager.user.application.service.UserAuthenticationService;
import br.com.task_manager.user.application.service.UserCreationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    private final UserAuthenticationService userAuthenticationService;
    private final UserCreationService userCreationService;

    public AuthenticationController(UserAuthenticationService userAuthenticationService, UserCreationService userCreationService) {
        this.userAuthenticationService = userAuthenticationService;
        this.userCreationService = userCreationService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiAuthenticateResponseDto> login(@RequestBody @Validated ApiAuthenticateRequestDto request) {
        ApiAuthenticateResponseDto response = ApiAuthMapper.toAuthenticateApiResponse(
            this.userAuthenticationService.authenticate(ApiAuthMapper.toAuthenticateCommand(request))
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiRegisterResponseDto> register(@RequestBody @Validated ApiRegisterRequestDto request) {
        ApiRegisterResponseDto response = ApiAuthMapper.toRegisterApiResponse(this.userCreationService.create(ApiAuthMapper.toRegisterCommand(request)));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
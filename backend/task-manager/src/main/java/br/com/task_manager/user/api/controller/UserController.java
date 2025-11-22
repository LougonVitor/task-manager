package br.com.task_manager.user.api.controller;

import br.com.task_manager.user.api.dto.UserRequestDto;
import br.com.task_manager.user.api.dto.UserResponseDto;
import br.com.task_manager.user.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser (@RequestBody UserRequestDto request) {
        UserResponseDto response = this.userService.createUser(request);
        return ResponseEntity.ok(response);
    }
}
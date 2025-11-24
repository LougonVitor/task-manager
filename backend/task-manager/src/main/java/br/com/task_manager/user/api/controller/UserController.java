package br.com.task_manager.user.api.controller;

import br.com.task_manager.user.api.dto.UserRequestDto;
import br.com.task_manager.user.api.dto.UserResponseDto;
import br.com.task_manager.user.api.mapper.UserMapper;
import br.com.task_manager.user.application.dto.CreateUserCommand;
import br.com.task_manager.user.application.dto.UserServiceResponseDto;
import br.com.task_manager.user.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
        CreateUserCommand userCommand = UserMapper.toCreateUserCommand(request);

        UserServiceResponseDto serviceResponse = this.userService.createUser(userCommand);

        UserResponseDto response = UserMapper.toResponseDto(serviceResponse);

        return ResponseEntity.ok(response);
    }
}
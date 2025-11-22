package br.com.task_manager.user.application.service;

import br.com.task_manager.user.api.dto.UserRequestDto;
import br.com.task_manager.user.api.dto.UserResponseDto;
import br.com.task_manager.user.domain.entity.UserEntity;
import br.com.task_manager.user.domain.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;

    public UserResponseDto createUser (UserRequestDto requestDto) {
        UserEntity userEntity = new UserEntity(requestDto.username(),requestDto.email(), requestDto.password());

        UserEntity createdUser = this.userRepository.createUser(userEntity);

        return new UserResponseDto(createdUser.getId(), createdUser.getUsername(), createdUser.getEmail());
    }
}
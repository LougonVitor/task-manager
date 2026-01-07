package br.com.task_manager.user.application.service;

import br.com.task_manager.user.application.dto.auth.CreateUserCommand;
import br.com.task_manager.user.application.dto.UserServiceResponseDto;
import br.com.task_manager.user.domain.entity.UserEntity;
import br.com.task_manager.user.domain.repository.IUserRepository;
import br.com.task_manager.user.domain.valueobject.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;

    public UserServiceResponseDto createUser (CreateUserCommand requestDto) {
        UserEntity userEntity = new UserEntity(requestDto.username(),requestDto.email(), requestDto.password(), UserRole.valueOf(requestDto.role()));

        UserEntity createdUser = this.userRepository.createUser(userEntity);

        return new UserServiceResponseDto(createdUser.getId(), createdUser.getUsername(), createdUser.getEmail());
    }
}
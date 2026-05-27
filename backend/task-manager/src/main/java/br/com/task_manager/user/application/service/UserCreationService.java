package br.com.task_manager.user.application.service;

import br.com.task_manager.user.application.dto.AppRegisterResponseDto;
import br.com.task_manager.user.application.dto.AppRegisterUserCommand;
import br.com.task_manager.user.application.mapper.AppAuthMapper;
import br.com.task_manager.user.domain.entity.UserEntity;
import br.com.task_manager.user.domain.exception.UserAlreadyExistsException;
import br.com.task_manager.user.domain.exception.UserCreationException;
import br.com.task_manager.user.domain.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCreationService {
    private final IUserRepository userRepository;

    public UserCreationService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AppRegisterResponseDto create(AppRegisterUserCommand command) {
        this.validateUserExistence(command.username());

        Optional<UserEntity> entity = this.userRepository.create(AppAuthMapper.toDomainEntity(command));

        return entity.map(AppAuthMapper::toAppRegisterResponseDto).orElseThrow(() -> new UserCreationException(command.username()));
    }

    private void validateUserExistence(String username) {
        Optional<UserEntity> entity = this.userRepository.findByUsername(username);

        if(entity.isPresent()) throw new UserAlreadyExistsException("User already exists!");
    }
}
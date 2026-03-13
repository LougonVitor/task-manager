package br.com.task_manager.user.application.service;

import br.com.task_manager.user.application.dto.CreateUserCommand;
import br.com.task_manager.user.application.mapper.AuthenticationMapper;
import br.com.task_manager.user.domain.exception.UserAlreadyExistsException;
import br.com.task_manager.user.domain.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CreateUserService {
    @Autowired
    private IUserRepository userRepository;

    public String createUser(CreateUserCommand command) {
        validateUserExistence(command.username());

        return this.userRepository.createUser(AuthenticationMapper.toEntity(command)).getUsername();
    }

    private void validateUserExistence(String username) {
        if(this.userRepository.findByUsername(username) != null) throw new UserAlreadyExistsException("User already exists!");
    }
}
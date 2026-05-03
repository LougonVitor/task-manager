package br.com.task_manager.user.application.service;

import br.com.task_manager.user.application.dto.CreateUserCommand;
import br.com.task_manager.user.application.mapper.AuthApplicationMapper;
import br.com.task_manager.user.domain.exception.UserAlreadyExistsException;
import br.com.task_manager.user.domain.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCreationService {
    @Autowired
    private IUserRepository userRepository;

    public String create(CreateUserCommand command) {
        validateUserExistence(command.username());

        return this.userRepository.create(AuthApplicationMapper.toEntity(command)).getUsername();
    }

    private void validateUserExistence(String username) {
        if(this.userRepository.findByUsername(username) != null) throw new UserAlreadyExistsException("User already exists!");
    }
}
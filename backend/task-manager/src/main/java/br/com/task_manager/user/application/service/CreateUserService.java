package br.com.task_manager.user.application.service;

import br.com.task_manager.user.application.dto.CreateUserCommand;
import br.com.task_manager.user.domain.entity.UserEntity;
import br.com.task_manager.user.domain.repository.IUserRepository;
import br.com.task_manager.user.domain.valueobject.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CreateUserService {
    @Autowired
    private IUserRepository userRepository;

    public String createUser(CreateUserCommand createUserCommand) throws Exception {
        if(this.userRepository.findByUsername(createUserCommand.username()) != null) throw new Exception("User already exists!");

        String encryptedPassword = new BCryptPasswordEncoder().encode(createUserCommand.password());

        UserEntity userToCreate = new UserEntity(createUserCommand.username(), createUserCommand.email(), encryptedPassword, UserRole.valueOf(createUserCommand.role()));

        return this.userRepository.createUser(userToCreate).getUsername();
    }
}
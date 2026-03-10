package br.com.task_manager.user.application.service;

import br.com.task_manager.user.application.dto.CreateUserCommand;
import br.com.task_manager.user.application.mapper.AuthenticationMapper;
import br.com.task_manager.user.domain.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CreateUserService {
    @Autowired
    private IUserRepository userRepository;

    public String createUser(CreateUserCommand command) throws Exception {
        if(this.userRepository.findByUsername(command.username()) != null) throw new Exception("User already exists!");

        return this.userRepository.createUser(AuthenticationMapper.toEntity(command)).getUsername();
    }
}
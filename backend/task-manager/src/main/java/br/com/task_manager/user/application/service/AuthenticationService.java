package br.com.task_manager.user.application.service;

import br.com.task_manager.user.application.dto.auth.CreateUserCommand;
import br.com.task_manager.user.application.dto.auth.AuthenticationUserCommand;
import br.com.task_manager.user.domain.entity.UserEntity;
import br.com.task_manager.user.domain.repository.IUserRepository;
import br.com.task_manager.user.domain.valueobject.UserRole;
import br.com.task_manager.user.infrastructure.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserRepository userRepository;

    public String loginAuthentication(AuthenticationUserCommand data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        String token =  this.tokenService.generateToken((User) auth.getPrincipal());

        System.out.println(token);

        return token;
    }

    public String createUser(CreateUserCommand createUserCommand) throws Exception {
        if(this.userRepository.findByUsername(createUserCommand.username()) != null) throw new Exception("User already exists!");

        String encryptedPassword = new BCryptPasswordEncoder().encode(createUserCommand.password());

        UserEntity userToCreate = new UserEntity(createUserCommand.username(), createUserCommand.email(), encryptedPassword, UserRole.valueOf(createUserCommand.role()));

        return this.userRepository.createUser(userToCreate).getUsername();
    }
}
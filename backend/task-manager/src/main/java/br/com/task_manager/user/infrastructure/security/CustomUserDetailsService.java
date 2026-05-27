package br.com.task_manager.user.infrastructure.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.task_manager.user.domain.entity.UserEntity;
import br.com.task_manager.user.domain.repository.IUserRepository;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final IUserRepository userRepository;

    public CustomUserDetailsService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = this.userRepository.findByUsername(username);

        return new UserSecurityDetails(userEntity.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username)));
    }
}
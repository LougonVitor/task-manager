package br.com.task_manager.user.infrastructure.repository;

import br.com.task_manager.user.domain.entity.UserEntity;
import br.com.task_manager.user.domain.repository.IUserRepository;
import br.com.task_manager.user.infrastructure.entity.UserJpaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class UserJpaAdapter implements IUserRepository {
    @Autowired
    private UserJpaRepository userJpaRepository;

    @Override
    public UserEntity createUser(UserEntity entity) {
        UserJpaEntity userJpaEntity = new UserJpaEntity();

        userJpaEntity.setUsername(entity.getUsername());
        userJpaEntity.setEmail(entity.getEmail());
        userJpaEntity.setPassword(entity.getPassword());
        userJpaEntity.setCreatedAt(LocalDateTime.now());
        userJpaEntity.setRole(entity.getRole().getRole());

        UserJpaEntity createdUser = this.userJpaRepository.save(userJpaEntity);

        return new UserEntity(
            createdUser.getId(),
            createdUser.getUsername(),
            createdUser.getEmail(),
            createdUser.getPassword(),
            createdUser.getCreatedAt()
        );
    }
}
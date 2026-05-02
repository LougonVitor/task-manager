package br.com.task_manager.user.infrastructure.repository;

import br.com.task_manager.user.domain.entity.UserEntity;
import br.com.task_manager.user.domain.repository.IUserRepository;
import br.com.task_manager.user.infrastructure.entity.UserJpaEntity;
import br.com.task_manager.user.infrastructure.mapper.JpaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserJpaAdapter implements IUserRepository {
    @Autowired
    private UserJpaRepository userJpaRepository;

    @Override
    public UserEntity createUser(UserEntity entity) {
        return JpaMapper.toEntity(this.userJpaRepository.save(new UserJpaEntity(entity)));
    }

    @Override
    public UserEntity findByUsername(String username) {
        UserJpaEntity userJpaEntity = this.userJpaRepository.findByUsername(username);

        if(userJpaEntity == null) return null;

        return JpaMapper.toEntity(userJpaEntity);
    }
}
package br.com.task_manager.user.infrastructure.repository;

import br.com.task_manager.user.domain.entity.UserEntity;
import br.com.task_manager.user.domain.repository.IUserRepository;
import br.com.task_manager.user.infrastructure.entity.UserJpaEntity;
import br.com.task_manager.user.infrastructure.mapper.InfraUserMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserJpaAdapter implements IUserRepository {
    private final IUserJpaRepository userJpaRepository;

    public UserJpaAdapter(IUserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public Optional<UserEntity> create(UserEntity entity) {
        return InfraUserMapper.toOptionalDomainEntity(this.userJpaRepository.save(new UserJpaEntity(entity)));
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        Optional<UserJpaEntity> entityFound = this.userJpaRepository.findByUsername(username);
        return entityFound.flatMap(InfraUserMapper::toOptionalDomainEntity);
    }
}
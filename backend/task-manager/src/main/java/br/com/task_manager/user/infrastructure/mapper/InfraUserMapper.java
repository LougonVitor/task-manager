package br.com.task_manager.user.infrastructure.mapper;

import br.com.task_manager.user.domain.entity.UserEntity;
import br.com.task_manager.user.domain.valueobject.UserRole;
import br.com.task_manager.user.infrastructure.entity.UserJpaEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class InfraUserMapper {
    /**
     * Converts a persistence-level {@link UserJpaEntity} retrieved from the database
     * into a domain-level Optional<{@link UserEntity}> used for business operations.
     *
     * @param jpaEntity the JPA entity retrieved from the persistence layer
     * @return a fully constructed domain Optional<{@code UserEntity}> containing all domain-level data and transformed role
     */
    public static Optional<UserEntity> toOptionalDomainEntity(UserJpaEntity jpaEntity) {
        return Optional.of(new UserEntity(
                jpaEntity.getId(),
                jpaEntity.getUsername(),
                jpaEntity.getEmail(),
                jpaEntity.getPassword(),
                UserRole.getEnumValue(jpaEntity.getRole()),
                jpaEntity.getCreatedAt()
        ));
    }
}
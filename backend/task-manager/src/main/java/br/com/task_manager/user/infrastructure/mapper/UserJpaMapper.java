package br.com.task_manager.user.infrastructure.mapper;

import br.com.task_manager.user.domain.entity.UserEntity;
import br.com.task_manager.user.domain.valueobject.UserRole;
import br.com.task_manager.user.infrastructure.entity.UserJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class UserJpaMapper {
    /**
     * Converts a persistence-level {@link UserJpaEntity} retrieved from the database
     * into a domain-level {@link UserEntity} used for business operations.
     *
     * @param jpaEntity the JPA entity retrieved from the persistence layer
     * @return a fully constructed domain {@code UserEntity} containing all domain-level data and transformed role
     */
    public static UserEntity toEntity(UserJpaEntity jpaEntity) {
        return new UserEntity(
                jpaEntity.getId(),
                jpaEntity.getUsername(),
                jpaEntity.getEmail(),
                jpaEntity.getPassword(),
                UserRole.getEnumValue(jpaEntity.getRole()),
                jpaEntity.getCreatedAt()
        );
    }
}
package br.com.task_manager.user.infrastructure.mapper;

import br.com.task_manager.user.domain.entity.UserEntity;
import br.com.task_manager.user.domain.valueobject.UserRole;
import br.com.task_manager.user.infrastructure.entity.UserJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class JpaMapper {
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
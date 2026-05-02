package br.com.task_manager.task.infrasctructure.mapper;

import br.com.task_manager.task.domain.entity.TaskEntity;
import br.com.task_manager.task.infrasctructure.entity.TaskJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class JpaEntityMapper {
    /**
     * JPA Entity Mapper
     * Map the JpaEntity from the database to Entity from DOMAIN
     */
    public static TaskEntity jpaEntityToDomainEntity(TaskJpaEntity jpaEntity) {
        return new TaskEntity(
            jpaEntity.getId(),
            jpaEntity.getTitle(),
            jpaEntity.getDescription(),
            jpaEntity.getTaskStatus(),
            jpaEntity.getCreatedAt(),
            jpaEntity.getDeadline(),
            jpaEntity.getCompletedAt(),
            jpaEntity.getUserId()
        );
    }
}
package br.com.task_manager.task.infrasctructure.mapper;

import br.com.task_manager.task.domain.entity.TaskEntity;
import br.com.task_manager.task.infrasctructure.entity.TaskJpaEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TaskInfraMapper {
    /**
     * Converts a persistence-level {@link TaskJpaEntity} retrieved from the database
     * into a domain-level {@link TaskEntity} to be used in the business logic layer.
     *
     * @param jpaEntity the JPA entity retrieved from the database
     * @return a fully constructed domain {@code TaskEntity} containing all domain-level data
     */
    public static TaskEntity toEntity(TaskJpaEntity jpaEntity) {
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

    public static Optional<TaskEntity> toOptionalEntity(TaskJpaEntity jpaEntity) {
        return Optional.of(new TaskEntity (
                jpaEntity.getId(),
                jpaEntity.getTitle(),
                jpaEntity.getDescription(),
                jpaEntity.getTaskStatus(),
                jpaEntity.getCreatedAt(),
                jpaEntity.getDeadline(),
                jpaEntity.getCompletedAt(),
                jpaEntity.getUserId()
        ));
    }

    /**
     * Converts a domain-level {@link TaskEntity} into a persistence-level {@link TaskJpaEntity}
     * to be saved or updated in the database.
     *
     * @param entity the domain entity representing the current state of the task
     * @return a corresponding {@code TaskJpaEntity} ready for database persistence
     */
    public static TaskJpaEntity toJpaEntity(TaskEntity entity) {
        return new TaskJpaEntity(entity);
    }

    public static Optional<TaskJpaEntity> toOptionalJpaEntity(TaskEntity entity) {
        return Optional.of(new TaskJpaEntity(entity));
    }
}
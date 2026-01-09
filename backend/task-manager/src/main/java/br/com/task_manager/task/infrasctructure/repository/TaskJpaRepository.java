package br.com.task_manager.task.infrasctructure.repository;

import br.com.task_manager.task.domain.entity.TaskEntity;
import br.com.task_manager.task.domain.repository.ITaskRepository;
import br.com.task_manager.task.infrasctructure.entity.TaskJpaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TaskJpaRepository implements ITaskRepository {
    @Autowired
    private ITaskJpaRepository taskJpaRepository;

    @Override
    public TaskEntity createTask(TaskEntity entity) {
        TaskJpaEntity jpaEntity = new TaskJpaEntity(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getTaskStatus(),
                entity.getCreatedAt(),
                entity.getDeadline(),
                entity.getCompletedAt()
        );

        TaskJpaEntity dbEntityCreated = this.taskJpaRepository.save(jpaEntity);

        return new TaskEntity(
                dbEntityCreated.getId(),
                dbEntityCreated.getTitle(),
                dbEntityCreated.getDescription(),
                dbEntityCreated.getTaskStatus(),
                dbEntityCreated.getCreatedAt(),
                dbEntityCreated.getDeadline(),
                dbEntityCreated.getCompletedAt()
        );
    }
}
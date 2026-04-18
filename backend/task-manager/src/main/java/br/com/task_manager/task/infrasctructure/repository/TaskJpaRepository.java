package br.com.task_manager.task.infrasctructure.repository;

import br.com.task_manager.task.domain.entity.TaskEntity;
import br.com.task_manager.task.domain.repository.ITaskRepository;
import br.com.task_manager.task.domain.valueobject.TaskStatus;
import br.com.task_manager.task.infrasctructure.entity.TaskJpaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskJpaRepository implements ITaskRepository {
    @Autowired
    private ITaskJpaRepository taskJpaRepository;

    @Override
    public List<TaskEntity> getAllTasks() {
        List<TaskJpaEntity> allJpaTasks = taskJpaRepository.findAll();

        if(!allJpaTasks.isEmpty()) {
            List<TaskEntity> allTasksEntity = new ArrayList<>();

            for(TaskJpaEntity jpaEntity : allJpaTasks) {
                allTasksEntity.add( new TaskEntity(
                        jpaEntity.getId()
                        , jpaEntity.getTitle()
                        , jpaEntity.getDescription()
                        , jpaEntity.getTaskStatus()
                        , jpaEntity.getCreatedAt()
                        , jpaEntity.getDeadline()
                        , jpaEntity.getCompletedAt()
                ));
            }

            return allTasksEntity;
        } else {
            return new ArrayList<TaskEntity>();
        }
    }

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

    @Override
    public void updateTaskStatus(long id) {
        Optional<TaskJpaEntity> entityFound = this.taskJpaRepository.findById(id);

        if(entityFound.isEmpty()) throw new RuntimeException("Task not found");

        entityFound.get().setTaskStatus(
                entityFound.get().getTaskStatus() == TaskStatus.COMPLETED
                    ? TaskStatus.IN_PROGRESS
                    : TaskStatus.COMPLETED
        );

        this.taskJpaRepository.save(entityFound.get());
    }

    @Override
    public void deleteById(long id) {
        Optional<TaskJpaEntity> entityFound = this.taskJpaRepository.findById(id);

        if(entityFound.isEmpty()) throw new RuntimeException("Task not found");

        this.taskJpaRepository.deleteById(id);
    }
}
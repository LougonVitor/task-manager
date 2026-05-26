package br.com.task_manager.task.infrasctructure.repository;

import br.com.task_manager.task.domain.entity.TaskEntity;
import br.com.task_manager.task.domain.repository.ITaskRepository;
import br.com.task_manager.task.infrasctructure.entity.TaskJpaEntity;
import br.com.task_manager.task.infrasctructure.mapper.TaskInfraMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TaskJpaRepository implements ITaskRepository {
    @Autowired
    private ITaskJpaRepository taskJpaRepository;

    @Override
    public List<TaskEntity> getAllTasks() {
        return this.taskJpaRepository.findAll().stream().map(TaskInfraMapper::toEntity).toList();
    }

    @Override
    public List<TaskEntity> findByUserId(Long userId) {
        return this.taskJpaRepository.findByUserId(userId).stream().map(TaskInfraMapper::toEntity).toList();
    }

    @Override
    public TaskEntity addNewTask(TaskEntity entityRequest) {
        TaskJpaEntity jpaEntityCreated = this.taskJpaRepository.save(new TaskJpaEntity(entityRequest));

        return TaskInfraMapper.toEntity(jpaEntityCreated);
    }

    @Override
    public void update(TaskEntity entityRequest) {
        this.taskJpaRepository.save(TaskInfraMapper.toJpaEntity(entityRequest));
    }

    @Override
    public void deleteById(long id) {
        this.taskJpaRepository.deleteById(id);
    }

    @Override
    public Optional<TaskEntity> findTaskById(Long id) {
        Optional<TaskJpaEntity> jpaEntity = this.taskJpaRepository.findById(id);
        return jpaEntity.map(TaskInfraMapper::toOptionalEntity).orElse(null);
    }
}
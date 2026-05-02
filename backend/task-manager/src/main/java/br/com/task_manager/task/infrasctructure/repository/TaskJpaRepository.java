package br.com.task_manager.task.infrasctructure.repository;

import br.com.task_manager.task.domain.entity.TaskEntity;
import br.com.task_manager.task.domain.repository.ITaskRepository;
import br.com.task_manager.task.infrasctructure.entity.TaskJpaEntity;
import br.com.task_manager.task.infrasctructure.mapper.JpaEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskJpaRepository implements ITaskRepository {
    @Autowired
    private ITaskJpaRepository taskJpaRepository;

    @Override
    public List<TaskEntity> getAllTasks() {
        return this.taskJpaRepository.findAll().stream().map(JpaEntityMapper::jpaEntityToDomainEntity).toList();
    }

    @Override
    public List<TaskEntity> findByUserId(Long userId) {
        return this.taskJpaRepository.findByUserId(userId).stream().map(JpaEntityMapper::jpaEntityToDomainEntity).toList();
    }

    @Override
    public TaskEntity createTask(TaskEntity entityRequest) {
        TaskJpaEntity jpaEntityCreated = this.taskJpaRepository.save(new TaskJpaEntity(entityRequest));

        return JpaEntityMapper.jpaEntityToDomainEntity(jpaEntityCreated);
    }

    @Override
    public TaskEntity updateTask(long id, TaskEntity entityRequest) {
        TaskJpaEntity jpaEntityFound = this.taskJpaRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found!"));

        jpaEntityFound.updateTaskData(entityRequest);

        return JpaEntityMapper.jpaEntityToDomainEntity(this.taskJpaRepository.save(jpaEntityFound));
    }

    @Override
    public void updateTaskStatus(long id) {
        TaskJpaEntity entityFound = this.taskJpaRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found!"));

        entityFound.toggleStatus();

        this.taskJpaRepository.save(entityFound);
    }

    @Override
    public void deleteById(long id) {
        this.taskJpaRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));

        this.taskJpaRepository.deleteById(id);
    }
}
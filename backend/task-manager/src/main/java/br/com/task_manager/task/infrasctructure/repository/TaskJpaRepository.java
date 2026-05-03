package br.com.task_manager.task.infrasctructure.repository;

import br.com.task_manager.task.domain.entity.TaskEntity;
import br.com.task_manager.task.domain.repository.ITaskRepository;
import br.com.task_manager.task.infrasctructure.entity.TaskJpaEntity;
import br.com.task_manager.task.infrasctructure.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskJpaRepository implements ITaskRepository {
    @Autowired
    private ITaskJpaRepository taskJpaRepository;

    @Override
    public List<TaskEntity> getAllTasks() {
        return this.taskJpaRepository.findAll().stream().map(TaskMapper::toEntity).toList();
    }

    @Override
    public List<TaskEntity> findByUserId(Long userId) {
        return this.taskJpaRepository.findByUserId(userId).stream().map(TaskMapper::toEntity).toList();
    }

    @Override
    public TaskEntity addNewTask(TaskEntity entityRequest) {
        TaskJpaEntity jpaEntityCreated = this.taskJpaRepository.save(new TaskJpaEntity(entityRequest));

        return TaskMapper.toEntity(jpaEntityCreated);
    }

    @Override
    public void updateData(long id, TaskEntity entityRequest) {
        TaskJpaEntity jpaEntityFound = TaskMapper.toJpaEntity(this.findById(id));
        jpaEntityFound.updateTaskData(entityRequest);

        this.taskJpaRepository.save(jpaEntityFound);
    }

    @Override
    public void deleteById(long id) {
        this.taskJpaRepository.deleteById(id);
    }

    @Override
    public TaskEntity findById(long id) {
        return TaskMapper.toEntity(
            this.taskJpaRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found!"))
        );
    }
}
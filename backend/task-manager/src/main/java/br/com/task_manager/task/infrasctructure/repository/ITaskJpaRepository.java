package br.com.task_manager.task.infrasctructure.repository;

import br.com.task_manager.task.infrasctructure.entity.TaskJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITaskJpaRepository extends JpaRepository<TaskJpaEntity, Long> {
    List<TaskJpaEntity> findByUserId(Long userId);
}
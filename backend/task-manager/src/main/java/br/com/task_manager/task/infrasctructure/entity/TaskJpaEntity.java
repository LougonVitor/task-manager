package br.com.task_manager.task.infrasctructure.entity;

import br.com.task_manager.task.domain.entity.TaskEntity;
import br.com.task_manager.task.domain.valueobject.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "tasks")
@Table(name = "tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class TaskJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private TaskStatus taskStatus;
    @Column(name = "dt_createdAt")
    private LocalDateTime createdAt;
    @Column(name = "dt_deadline")
    private LocalDate deadline;
    @Column(name = "dt_completed")
    private LocalDateTime completedAt;
    @Column(name = "id_user")
    private Long userId;

    public TaskJpaEntity(TaskEntity entity) {
        this.setId(entity.getId());
        this.setTitle(entity.getTitle());
        this.setDescription(entity.getDescription());
        this.setTaskStatus(entity.getTaskStatus());
        this.setCreatedAt(entity.getCreatedAt());
        this.setDeadline(entity.getDeadline());
        this.setCompletedAt(entity.getCompletedAt());
        this.setUserId(entity.getUserId());
    }

    public void updateTaskData(TaskEntity entityRequest) {
        this.setTitle(entityRequest.getTitle());
        this.setDescription(entityRequest.getDescription());
        this.setDeadline(entityRequest.getDeadline());
        if(entityRequest.getTaskStatus() != null) this.setTaskStatus(entityRequest.getTaskStatus());
    }
}
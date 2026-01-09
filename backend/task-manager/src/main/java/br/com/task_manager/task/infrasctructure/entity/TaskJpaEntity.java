package br.com.task_manager.task.infrasctructure.entity;

import br.com.task_manager.task.domain.valueobject.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
    private LocalDateTime deadline;
    @Column(name = "dt_completed")
    private LocalDateTime completedAt;
}
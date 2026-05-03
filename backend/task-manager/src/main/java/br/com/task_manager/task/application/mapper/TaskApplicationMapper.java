package br.com.task_manager.task.application.mapper;

import br.com.task_manager.task.application.dto.CreateTaskCommand;
import br.com.task_manager.task.application.dto.MutateTaskResponseDto;
import br.com.task_manager.task.domain.entity.TaskEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskApplicationMapper {
    /**
     * Converts a {@link CreateTaskCommand} containing the details of the task
     * creation request into a domain-level {@link TaskEntity}. It initializes the
     * creation timestamp using the current system time.
     *
     * @param command the command object containing data for the new task
     * @return a fully constructed {@code TaskEntity} ready for business operations
     */
    public static TaskEntity toEntity(CreateTaskCommand command) {
        return new TaskEntity(
                command.title(),
                command.description(),
                command.status(),
                LocalDateTime.now(),
                command.deadline(),
                command.userId()
        );
    }

    /**
     * Converts a domain-level {@link TaskEntity} into a {@link MutateTaskResponseDto}
     * to be returned to the application or presentation layers after an operation.
     *
     * @param entity the domain entity representing the current state of the task
     * @return a {@code TaskMutationResponse} carrying the identifying details of the mutation
     */
    public static MutateTaskResponseDto toMutationResponse(TaskEntity entity) {
        return new MutateTaskResponseDto(
                entity.getId(),
                entity.getTitle(),
                entity.getCreatedAt()
        );
    }
}
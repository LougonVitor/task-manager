package br.com.task_manager.task.application.mapper;

import br.com.task_manager.task.application.dto.CreateTaskCommand;
import br.com.task_manager.task.domain.entity.TaskEntity;

import java.time.LocalDateTime;

public class DtoToTaskMapper {
    /**
     * Create Task Command Mapper
     * Map the command for task creation to the domain entity
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
}
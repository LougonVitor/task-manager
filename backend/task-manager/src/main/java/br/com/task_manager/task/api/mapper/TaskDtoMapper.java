package br.com.task_manager.task.api.mapper;

import br.com.task_manager.task.api.dto.TaskRequestDto;
import br.com.task_manager.task.api.dto.TaskResponseDto;
import br.com.task_manager.task.application.dto.CreateResponseTaskCommand;
import br.com.task_manager.task.application.dto.CreateTaskCommand;
import br.com.task_manager.task.application.dto.UpdateTaskCommand;

public class TaskDtoMapper {
    public static TaskResponseDto toResponse (CreateResponseTaskCommand command) {
        return new TaskResponseDto(
            command.id(),
            command.title(),
            command.createdAt());
    }

    public static CreateTaskCommand toCreateCommand(TaskRequestDto request) {
        return new CreateTaskCommand(
            request.title(),
            request.description(),
            request.status(),
            request.deadline(),
            request.userId());
    }

    public static UpdateTaskCommand toUpdateCommand(TaskRequestDto request) {
        return new UpdateTaskCommand(
            request.title(),
            request.description(),
            request.status(),
            request.deadline()
        );
    }
}
package br.com.task_manager.task.api.mapper;

import br.com.task_manager.task.api.dto.TaskRequestDto;
import br.com.task_manager.task.api.dto.TaskResponseDto;
import br.com.task_manager.task.application.dto.MutateTaskResponseDto;
import br.com.task_manager.task.application.dto.CreateTaskCommand;
import br.com.task_manager.task.application.dto.UpdateTaskCommand;
import org.springframework.stereotype.Component;

/**
 * Mapper component responsible for converting Data Transfer Objects (DTOs)
 * between the API layer and the Application/Domain layer.
 */
@Component
public class TaskApiMapper {
    /**
     * Converts a {@link MutateTaskResponseDto} from the application layer
     * into a {@link TaskResponseDto} to be returned by the API.
     *
     * @param command the mutation response containing the processed task data
     * @return a corresponding {@code TaskResponse} object
     */
    public static TaskResponseDto toResponse (MutateTaskResponseDto command) {
        return new TaskResponseDto(
            command.id(),
            command.title(),
            command.createdAt());
    }

    /**
     * Converts an incoming {@link TaskRequestDto} from the API layer
     * into a {@link CreateTaskCommand} for the application layer.
     *
     * @param request the incoming HTTP request data
     * @return a {@code CreateTaskCommand} containing the details to create a new task
     */
    public static CreateTaskCommand toCreateCommand(TaskRequestDto request) {
        return new CreateTaskCommand(
            request.title(),
            request.description(),
            request.status(),
            request.deadline());
    }

    /**
     * Converts an incoming {@link TaskRequestDto} from the API layer
     * into an {@link UpdateTaskCommand} for the application layer.
     *
     * @param request the incoming HTTP request data containing the updated fields
     * @return an {@code UpdateTaskCommand} containing the details to modify the task
     */
    public static UpdateTaskCommand toUpdateCommand(TaskRequestDto request) {
        return new UpdateTaskCommand(
            request.title(),
            request.description(),
            request.status(),
            request.deadline()
        );
    }
}
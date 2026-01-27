package br.com.task_manager.task.api.mapper;

import br.com.task_manager.task.api.dto.TaskResponseDto;
import br.com.task_manager.task.application.dto.CreateResponseTaskCommand;

public class TaskMapper {
    public static TaskResponseDto toTaskResponse (CreateResponseTaskCommand request) {
        return new TaskResponseDto(request.id(), request.title(), request.createdAt());
    }
}
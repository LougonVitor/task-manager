package br.com.task_manager.task.application.service;

import br.com.task_manager.task.application.dto.MutateTaskResponseDto;
import br.com.task_manager.task.application.dto.CreateTaskCommand;
import br.com.task_manager.task.application.mapper.TaskApplicationMapper;
import br.com.task_manager.task.domain.entity.TaskEntity;
import br.com.task_manager.task.domain.repository.ITaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskCreationService {
    private final ITaskRepository taskRepository;

    public TaskCreationService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public MutateTaskResponseDto save(CreateTaskCommand command, Long userId) {
        TaskEntity response = this.taskRepository.addNewTask(TaskApplicationMapper.toEntity(command, userId));

        return TaskApplicationMapper.toMutationResponse(response);
    }
}
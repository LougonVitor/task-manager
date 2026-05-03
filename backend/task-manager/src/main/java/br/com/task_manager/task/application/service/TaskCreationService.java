package br.com.task_manager.task.application.service;

import br.com.task_manager.task.application.dto.MutateTaskResponseDto;
import br.com.task_manager.task.application.dto.CreateTaskCommand;
import br.com.task_manager.task.application.mapper.TaskApplicationMapper;
import br.com.task_manager.task.domain.entity.TaskEntity;
import br.com.task_manager.task.domain.repository.ITaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskCreationService {
    @Autowired
    private ITaskRepository taskRepository;

    public MutateTaskResponseDto save(CreateTaskCommand command) {
        TaskEntity response = this.taskRepository.addNewTask(TaskApplicationMapper.toEntity(command));

        return TaskApplicationMapper.toMutationResponse(response);
    }
}
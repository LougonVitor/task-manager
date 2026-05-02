package br.com.task_manager.task.application.service;

import br.com.task_manager.task.application.dto.CreateResponseTaskCommand;
import br.com.task_manager.task.application.dto.CreateTaskCommand;
import br.com.task_manager.task.application.mapper.DtoToTaskMapper;
import br.com.task_manager.task.application.mapper.TaskToDtoMapper;
import br.com.task_manager.task.domain.entity.TaskEntity;
import br.com.task_manager.task.domain.repository.ITaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskCreationService {
    @Autowired
    private ITaskRepository taskRepository;

    public CreateResponseTaskCommand createTask(CreateTaskCommand command) {
        TaskEntity response = this.taskRepository.createTask(DtoToTaskMapper.toEntity(command));

        return TaskToDtoMapper.toCreateResponseCommand(response);
    }
}
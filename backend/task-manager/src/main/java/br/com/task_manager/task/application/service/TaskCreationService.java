package br.com.task_manager.task.application.service;

import br.com.task_manager.task.application.dto.TaskMutationResponse;
import br.com.task_manager.task.application.dto.CreateTaskCommand;
import br.com.task_manager.task.application.mapper.TaskMapper;
import br.com.task_manager.task.domain.entity.TaskEntity;
import br.com.task_manager.task.domain.repository.ITaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskCreationService {
    @Autowired
    private ITaskRepository taskRepository;

    public TaskMutationResponse saveTask(CreateTaskCommand command) {
        TaskEntity response = this.taskRepository.addNewTask(TaskMapper.toEntity(command));

        return TaskMapper.toMutationResponse(response);
    }
}
package br.com.task_manager.task.application.service;

import br.com.task_manager.task.application.dto.CreateResponseTaskCommand;
import br.com.task_manager.task.application.dto.CreateTaskCommand;
import br.com.task_manager.task.domain.entity.TaskEntity;
import br.com.task_manager.task.domain.repository.ITaskRepository;
import br.com.task_manager.task.domain.valueobject.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TaskService {
    @Autowired
    private ITaskRepository taskRepository;

    public CreateResponseTaskCommand createTask(CreateTaskCommand request) {
        TaskEntity taskEntity = new TaskEntity();

        taskEntity.setTitle(request.title());
        taskEntity.setDescription(request.description());
        taskEntity.setTaskStatus(TaskStatus.getEnumValue(request.status()));
        taskEntity.setCreatedAt(LocalDateTime.now());
        taskEntity.setDeadline(request.deadline());

        TaskEntity response = this.taskRepository.createTask(taskEntity);

        return new CreateResponseTaskCommand(response.getId(), response.getTitle(), response.getCreatedAt());
    }
}
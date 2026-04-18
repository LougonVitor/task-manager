package br.com.task_manager.task.application.service;

import br.com.task_manager.task.application.dto.*;
import br.com.task_manager.task.domain.entity.TaskEntity;
import br.com.task_manager.task.domain.repository.ITaskRepository;
import br.com.task_manager.task.domain.valueobject.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private ITaskRepository taskRepository;

    public List<TaskResponse> getAllTasks() {
        List<TaskEntity> allTasksEntity = taskRepository.getAllTasks();

        List<TaskResponse> response = new ArrayList<>();

        if(allTasksEntity.isEmpty()) return response;

        for(TaskEntity entity : allTasksEntity) {
            response.add(new TaskResponse(entity));
        }

        return response;
    }

    public CreateResponseTaskCommand createTask(CreateTaskCommand request) {
        TaskEntity taskEntity = new TaskEntity();

        taskEntity.setTitle(request.title());
        taskEntity.setDescription(request.description());
        taskEntity.setTaskStatus(TaskStatus.getEnumValue(request.status().toUpperCase()));
        taskEntity.setCreatedAt(LocalDateTime.now());
        taskEntity.setDeadline(request.deadline());

        TaskEntity response = this.taskRepository.createTask(taskEntity);

        return new CreateResponseTaskCommand(response.getId(), response.getTitle(), response.getCreatedAt());
    }

    public UpdateResponseTaskCommand updateTask(long id, UpdateTaskCommand command) {
        TaskEntity taskEntity = new TaskEntity();

        taskEntity.setTitle(command.title());
        taskEntity.setDescription(command.description());
        taskEntity.setDeadline(command.deadline());

        TaskEntity response = this.taskRepository.updateTask(id, taskEntity);

        return new UpdateResponseTaskCommand(response.getId(), response.getTitle(), response.getCreatedAt());
    }

    public void updateTaskStatus(long id) {
        this.taskRepository.updateTaskStatus(id);
    }

    public void deleteById(long id) {
        this.taskRepository.deleteById(id);
    }
}
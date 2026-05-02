package br.com.task_manager.task.application.service;

import br.com.task_manager.task.application.dto.*;
import br.com.task_manager.task.domain.entity.TaskEntity;
import br.com.task_manager.task.domain.repository.ITaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskRecoveryService {
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

    public List<TaskResponse> getTasksByUserId(Long userId) {
        List<TaskEntity> allTasksEntity = taskRepository.findByUserId(userId);

        List<TaskResponse> response = new ArrayList<>();

        if(allTasksEntity.isEmpty()) return response;

        for(TaskEntity entity : allTasksEntity) {
            response.add(new TaskResponse(entity));
        }

        return response;
    }
}
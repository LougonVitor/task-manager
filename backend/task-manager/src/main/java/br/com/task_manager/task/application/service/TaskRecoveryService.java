package br.com.task_manager.task.application.service;

import br.com.task_manager.task.application.dto.*;
import br.com.task_manager.task.domain.repository.ITaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskRecoveryService {
    @Autowired
    private ITaskRepository taskRepository;

    public List<TaskResponse> getAllTasks() {
        return this.taskRepository.getAllTasks().stream().map(TaskResponse::new).toList();
    }

    public List<TaskResponse> getCurrentUserTasks(Long userId) {
        return  this.taskRepository.findByUserId(userId).stream().map(TaskResponse::new).toList();
    }
}
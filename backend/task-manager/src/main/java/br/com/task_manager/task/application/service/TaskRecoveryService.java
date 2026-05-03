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

    public List<TaskApplicationResponseDto> getAllTasks() {
        return this.taskRepository.getAllTasks().stream().map(TaskApplicationResponseDto::new).toList();
    }

    public List<TaskApplicationResponseDto> getCurrentUserTasks(Long userId) {
        return  this.taskRepository.findByUserId(userId).stream().map(TaskApplicationResponseDto::new).toList();
    }
}
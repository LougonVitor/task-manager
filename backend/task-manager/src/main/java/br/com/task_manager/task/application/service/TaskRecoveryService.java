package br.com.task_manager.task.application.service;

import br.com.task_manager.task.application.dto.*;
import br.com.task_manager.task.application.mapper.TaskApplicationMapper;
import br.com.task_manager.task.domain.repository.ITaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskRecoveryService {
    private final ITaskRepository taskRepository;

    public TaskRecoveryService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskApplicationResponseDto> getAllTasks() {
        return TaskApplicationMapper.toListOfTaskResponse(this.taskRepository.getAllTasks());
    }

    public List<TaskApplicationResponseDto> getCurrentUserTasks(Long userId) {
        return TaskApplicationMapper.toListOfTaskResponse(this.taskRepository.findByUserId(userId));
    }
}
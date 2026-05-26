package br.com.task_manager.task.api.controller;

import br.com.task_manager.task.api.dto.TaskRequestDto;
import br.com.task_manager.task.api.dto.TaskResponseDto;
import br.com.task_manager.task.api.mapper.TaskApiMapper;
import br.com.task_manager.task.application.dto.MutateTaskResponseDto;
import br.com.task_manager.task.application.dto.TaskApplicationResponseDto;
import br.com.task_manager.task.application.service.TaskCreationService;
import br.com.task_manager.task.application.service.TaskDeletionService;
import br.com.task_manager.task.application.service.TaskRecoveryService;
import br.com.task_manager.task.application.service.TaskUpdateService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("task")
public class TaskController {
    @Autowired
    private TaskRecoveryService taskRecoveryService;
    @Autowired
    private TaskCreationService taskCreationService;
    @Autowired
    private TaskDeletionService taskDeletionService;
    @Autowired
    private TaskUpdateService taskUpdateService;


    @GetMapping
    public List<TaskApplicationResponseDto> getAllTasks() {
        return taskRecoveryService.getAllTasks();
    }

    @GetMapping("/my-tasks")
    public List<TaskApplicationResponseDto> getCurrentUserTasks(Authentication authentication) {
        return taskRecoveryService.getCurrentUserTasks((Long) authentication.getPrincipal());
    }

    @PostMapping("/create")
    public TaskResponseDto createTask(@Valid @RequestBody TaskRequestDto request, Authentication authentication) {
        MutateTaskResponseDto response = this.taskCreationService.save(TaskApiMapper.toCreateCommand(request), (Long) authentication.getPrincipal());

        return TaskApiMapper.toResponse(response);
    }

    @PutMapping("/{id}")
    public void updateTask(@PathVariable long id, @Valid @RequestBody TaskRequestDto request, Authentication authentication) {
        this.taskUpdateService.updateData(id, TaskApiMapper.toUpdateCommand(request), (Long) authentication.getPrincipal());
    }

    @PutMapping("/{id}/status")
    public void updateTaskStatus(@PathVariable long id, Authentication authentication) {
        this.taskUpdateService.toggleStatus(id, (Long) authentication.getPrincipal());
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable long id, Authentication authentication) {
        this.taskDeletionService.deleteById(id, (Long) authentication.getPrincipal());
    }
}
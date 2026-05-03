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
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{userId}")
    public List<TaskApplicationResponseDto> getCurrentUserTasks(@PathVariable Long userId) {
        return taskRecoveryService.getCurrentUserTasks(userId);
    }

    @PostMapping("/create")
    public TaskResponseDto createTask(@RequestBody TaskRequestDto request) {
        MutateTaskResponseDto response = this.taskCreationService.save(TaskApiMapper.toCreateCommand(request));

        return TaskApiMapper.toResponse(response);
    }

    @PutMapping("/{id}")
    public void updateTask(@PathVariable long id, @RequestBody TaskRequestDto request) {
        this.taskUpdateService.update(id, TaskApiMapper.toUpdateCommand(request));
    }

    @PutMapping("/{id}/status")
    public void updateTaskStatus(@PathVariable long id) {
        this.taskUpdateService.updateTaskStatus(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable long id) {
        this.taskDeletionService.deleteByTaskId(id);
    }
}
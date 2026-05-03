package br.com.task_manager.task.api.controller;

import br.com.task_manager.task.api.dto.TaskRequest;
import br.com.task_manager.task.api.dto.TaskResponse;
import br.com.task_manager.task.api.mapper.TaskMapper;
import br.com.task_manager.task.application.dto.TaskMutationResponse;
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
    private TaskRecoveryService recoveryService;
    @Autowired
    private TaskCreationService creationService;
    @Autowired
    private TaskDeletionService deletionService;
    @Autowired
    private TaskUpdateService updateService;


    @GetMapping
    public List<br.com.task_manager.task.application.dto.TaskResponse> getAllTasks() {
        return recoveryService.getAllTasks();
    }

    @GetMapping("/{userId}")
    public List<br.com.task_manager.task.application.dto.TaskResponse> getCurrentUserTasks(@PathVariable Long userId) {
        return recoveryService.getCurrentUserTasks(userId);
    }

    @PostMapping("/create")
    public TaskResponse createTask(@RequestBody TaskRequest request) {
        TaskMutationResponse response = this.creationService.saveTask(TaskMapper.toCreateCommand(request));

        return TaskMapper.toResponse(response);
    }

    @PutMapping("/{id}")
    public void updateTask(@PathVariable long id, @RequestBody TaskRequest request) {
        this.updateService.updateTask(id, TaskMapper.toUpdateCommand(request));
    }

    @PutMapping("/{id}/status")
    public void updateTaskStatus(@PathVariable long id) {
        this.updateService.updateTaskStatus(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable long id) {
        this.deletionService.deleteByTaskId(id);
    }
}
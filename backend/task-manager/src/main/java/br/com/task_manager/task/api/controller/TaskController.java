package br.com.task_manager.task.api.controller;

import br.com.task_manager.task.api.dto.TaskRequestDto;
import br.com.task_manager.task.api.dto.TaskResponseDto;
import br.com.task_manager.task.api.mapper.TaskMapper;
import br.com.task_manager.task.application.dto.CreateResponseTaskCommand;
import br.com.task_manager.task.application.dto.CreateTaskCommand;
import br.com.task_manager.task.application.dto.TaskResponse;
import br.com.task_manager.task.application.dto.UpdateTaskCommand;
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
    public List<TaskResponse> getAllTasks() {
        return recoveryService.getAllTasks();
    }

    @GetMapping("/{userId}")
    public List<TaskResponse> getTasksByUserId(@PathVariable Long userId) {
        return recoveryService.getTasksByUserId(userId);
    }

    @PostMapping("/create")
    public TaskResponseDto createTask(@RequestBody TaskRequestDto request) {
        CreateTaskCommand taskCommand = new CreateTaskCommand(request.title(), request.description(), request.status(), request.deadline(), request.userId());

        CreateResponseTaskCommand response = this.creationService.createTask(taskCommand);

        return TaskMapper.toTaskResponse(response);
    }

    @PutMapping("/{id}")
    public void updateTask(@PathVariable long id, @RequestBody TaskRequestDto request) {
        UpdateTaskCommand taskCommand = new UpdateTaskCommand(request.title(), request.description(), request.status(), request.deadline());
        this.updateService.updateTask(id, taskCommand);
    }

    @PutMapping("/{id}/status")
    public void updateTaskStatus(@PathVariable long id) {
        this.updateService.updateTaskStatus(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable long id) {
        this.deletionService.deleteById(id);
    }
}
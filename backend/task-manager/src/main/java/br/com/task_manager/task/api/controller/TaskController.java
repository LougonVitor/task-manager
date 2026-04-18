package br.com.task_manager.task.api.controller;

import br.com.task_manager.task.api.dto.TaskRequestDto;
import br.com.task_manager.task.api.dto.TaskResponseDto;
import br.com.task_manager.task.api.mapper.TaskMapper;
import br.com.task_manager.task.application.dto.CreateResponseTaskCommand;
import br.com.task_manager.task.application.dto.CreateTaskCommand;
import br.com.task_manager.task.application.dto.TaskResponse;
import br.com.task_manager.task.application.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<TaskResponse> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping("/create")
    public TaskResponseDto createTask(@RequestBody TaskRequestDto request) {
        CreateTaskCommand taskCommand = new CreateTaskCommand(request.title(), request.description(), request.status(), request.deadline());

        CreateResponseTaskCommand response = this.taskService.createTask(taskCommand);

        return TaskMapper.toTaskResponse(response);
    }
}
package br.com.task_manager.task.api.controller;

import br.com.task_manager.task.api.dto.TaskRequestDto;
import br.com.task_manager.task.application.dto.CreateTaskCommand;
import br.com.task_manager.task.application.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    public Long createTask(@RequestBody TaskRequestDto request) {
        CreateTaskCommand taskCommand = new CreateTaskCommand(request.title(), request.description(), request.status(), request.deadline());



        return this.taskService.createTask(taskCommand);
    }
}
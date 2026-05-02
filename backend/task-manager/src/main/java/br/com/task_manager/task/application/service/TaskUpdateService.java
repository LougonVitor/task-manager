package br.com.task_manager.task.application.service;

import br.com.task_manager.task.application.dto.UpdateResponseTaskCommand;
import br.com.task_manager.task.application.dto.UpdateTaskCommand;
import br.com.task_manager.task.domain.entity.TaskEntity;
import br.com.task_manager.task.domain.repository.ITaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskUpdateService {
    @Autowired
    private ITaskRepository taskRepository;

    public UpdateResponseTaskCommand updateTask(long id, UpdateTaskCommand command) {
        TaskEntity taskEntity = new TaskEntity();

        taskEntity.setTitle(command.title());
        taskEntity.setDescription(command.description());
        taskEntity.setDeadline(command.deadline());

        TaskEntity response = this.taskRepository.updateTask(id, taskEntity);

        return new UpdateResponseTaskCommand(response.getId(), response.getTitle(), response.getCreatedAt());
    }

    public void updateTaskStatus(long id) {
        this.taskRepository.updateTaskStatus(id);
    }
}

package br.com.task_manager.task.application.service;

import br.com.task_manager.task.domain.repository.ITaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskDeletionService {
    @Autowired
    private ITaskRepository taskRepository;

    public void deleteByTaskId(long id) {
        this.taskRepository.findById(id);
        this.taskRepository.deleteById(id);
    }
}
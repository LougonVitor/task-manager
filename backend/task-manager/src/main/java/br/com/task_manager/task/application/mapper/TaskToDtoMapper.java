package br.com.task_manager.task.application.mapper;

import br.com.task_manager.task.application.dto.CreateResponseTaskCommand;
import br.com.task_manager.task.application.dto.UpdateResponseTaskCommand;
import br.com.task_manager.task.domain.entity.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskToDtoMapper {
    /**
     * Entity Mapper
     * Map the domain entity to update response task command from APPLICATION/DTO
     */
    public static UpdateResponseTaskCommand toUpdateResponseCommand(TaskEntity entity) {
        return new UpdateResponseTaskCommand(
                entity.getId(),
                entity.getTitle(),
                entity.getCreatedAt()
        );
    }

    /**
     * Entity Mapper
     * Map the domain entity to CreateResponseTaskCommand from APPLICATION/DTO
     */
    public static CreateResponseTaskCommand toCreateResponseCommand (TaskEntity entity) {
        return new CreateResponseTaskCommand(
                entity.getId(),
                entity.getTitle(),
                entity.getCreatedAt()
        );
    }
}
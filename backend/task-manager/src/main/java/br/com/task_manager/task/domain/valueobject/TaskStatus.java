package br.com.task_manager.task.domain.valueobject;

public enum TaskStatus {
    COMPLETED("completed"),
    IN_PROGRESS("in_progress");

    private final String status;

    TaskStatus (String status) {
        this.status = status;
    }

    public String getRole() {
        return this.status;
    }

    public static TaskStatus getEnumValue(String status) {
        return TaskStatus.valueOf(status);
    }
}
package task.memento;

import task.model.Subtask;
import task.model.Task;
import task.state.TaskState;
import task.strategy.ITaskPriorityStrategy;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class TaskMemento {
    private final String taskId;
    private final String description;
    private final String responsiblePerson;
    private final double progress;
    private final TaskState state;
    private final ITaskPriorityStrategy taskPriorityStrategy;
    private final LocalDate deadline;
    private final int complexity;
    private final boolean approved;
    private final List<SubtaskMemento> subtasks;


    public String getTaskId() {
        return taskId;
    }

    public String getDescription() {
        return description;
    }

    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    public double getProgress() {
        return progress;
    }

    public TaskState getState() {
        return state;
    }

    public ITaskPriorityStrategy getTaskPriorityStrategy() {
        return taskPriorityStrategy;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public int getComplexity() {
        return complexity;
    }

    public boolean isApproved() {
        return approved;
    }

    public List<SubtaskMemento> getSubtasks() {
        return subtasks;
    }

    public TaskMemento(String taskId, String description, String responsiblePerson,
                       double progress, TaskState state, ITaskPriorityStrategy taskPriorityStrategy,
                       LocalDate deadline, int complexity, boolean approved, List<Subtask> subtasks) {
        this.taskId = taskId;
        this.description = description;
        this.responsiblePerson = responsiblePerson;
        this.progress = progress;
        this.state = state;
        this.taskPriorityStrategy = taskPriorityStrategy;
        this.deadline = deadline;
        this.complexity = complexity;
        this.approved = approved;
        this.subtasks = subtasks.stream()
                .map(subtask -> SubtaskMemento.fromSubtask(subtask))
                .collect(Collectors.toList());
    }


}

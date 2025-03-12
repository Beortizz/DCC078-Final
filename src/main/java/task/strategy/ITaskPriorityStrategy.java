package task.strategy;

import task.model.Task;

import java.util.List;

public interface ITaskPriorityStrategy {
    int calculatePriority(Task task);
}
package task.model;

import task.model.Subtask;

public interface ITask {
    void addSubtask(Subtask subtask);
    void removeSubtask(Subtask subtask);
    int calculatePriority();
}
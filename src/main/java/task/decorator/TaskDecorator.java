package task.decorator;

import task.model.ITask;
import task.model.Subtask;

public abstract class TaskDecorator implements ITask {
    protected ITask decoratedTask;

    public TaskDecorator(ITask decoratedTask) {
        this.decoratedTask = decoratedTask;
    }

    @Override
    public void addSubtask(Subtask subtask) {
        decoratedTask.addSubtask(subtask);
    }
    public @Override
    void removeSubtask(Subtask subtask) {
        decoratedTask.removeSubtask(subtask);
    }
    public @Override
    int calculatePriority() {
        return decoratedTask.calculatePriority();
    }

}

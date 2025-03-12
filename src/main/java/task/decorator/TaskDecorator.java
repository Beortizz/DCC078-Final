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

    @Override
    public void removeSubtask(Subtask subtask) {
        decoratedTask.removeSubtask(subtask);
    }

    @Override
    public void printTaskDetails() {
        decoratedTask.printTaskDetails();
    }
}

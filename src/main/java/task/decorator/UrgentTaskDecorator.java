package task.decorator;

import task.model.ITask;
import task.model.Subtask;

public class UrgentTaskDecorator extends TaskDecorator {
    public UrgentTaskDecorator(ITask decoratedTask) {
        super(decoratedTask);
    }

    @Override
    public void printTaskDetails() {
        super.printTaskDetails();
    }

    @Override
    public void addSubtask(Subtask subtask) {
        super.addSubtask(subtask);
    }

    @Override
    public void removeSubtask(Subtask subtask) {
        super.removeSubtask(subtask);
    }

    @Override
    public int calculatePriority() {
        return decoratedTask.calculatePriority();
    }
}

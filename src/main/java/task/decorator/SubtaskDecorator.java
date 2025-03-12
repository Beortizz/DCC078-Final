package task.decorator;

import task.model.ISubtask;

public class SubtaskDecorator implements ISubtask {
    private ISubtask decoratedSubtask;

    public SubtaskDecorator(ISubtask decoratedSubtask) {
        this.decoratedSubtask = decoratedSubtask;
    }

    @Override
    public String getName() {
        return decoratedSubtask.getName();
    }

    @Override
    public String getDescription() {
        return decoratedSubtask.getDescription();
    }

    @Override
    public int getHoursNeeded() {
        return decoratedSubtask.getHoursNeeded();
    }
}

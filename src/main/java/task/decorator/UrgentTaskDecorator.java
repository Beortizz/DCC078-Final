package task.decorator;

import task.model.ITask;
import task.model.Subtask;

import java.util.ArrayList;
import java.util.List;

public class UrgentTaskDecorator extends TaskDecorator {

    private List<Subtask> subtasks;
    public UrgentTaskDecorator(ITask decoratedTask) {
        super(decoratedTask);
        this.setSubtasks(new ArrayList<>());
    }

    @Override
    public void addSubtask(Subtask subtask) {
        super.addSubtask(subtask);
        this.subtasks.add(subtask);
    }

    @Override
    public void removeSubtask(Subtask subtask) {
        super.removeSubtask(subtask);
        this.subtasks.remove(subtask);
    }

    @Override
    public int calculatePriority() {
        return 200;
    }
    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<Subtask> subtasks) {
        this.subtasks = subtasks;
    }
}

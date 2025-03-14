package task.model;

import task.memento.DigitalTaskMemento;
import task.memento.TaskMemento;
import task.strategy.ITaskPriorityStrategy;

import java.time.LocalDate;

public class DigitalTask extends Task {
    private String accessLink;

    public DigitalTask(String taskId, String description, String responsiblePerson, String accessLink, LocalDate deadline, int complexity, ITaskPriorityStrategy taskPriorityStrategy) {
        super(taskId, description, responsiblePerson, deadline, complexity, taskPriorityStrategy);
        this.accessLink = accessLink;
    }

    public String getAccessLink() {
        return accessLink;
    }

    public void setAccessLink(String accessLink) {
        this.accessLink = accessLink;
    }

    @Override
    protected int getWorkDayDuration() {
        return 6;
    }

    @Override
    protected int getWorkWeekDuration() {
        return 5;
    }

    @Override
    public TaskMemento saveToMemento() {
        TaskMemento taskMemento = super.saveToMemento();

        return new DigitalTaskMemento(taskMemento, this.accessLink);
    }

    @Override
    public void restoreFromMemento(TaskMemento taskMemento) {
        super.restoreFromMemento(taskMemento);
        DigitalTaskMemento digitalTaskMemento = (DigitalTaskMemento) taskMemento;
        this.accessLink = digitalTaskMemento.getAccessLink();
    }
}
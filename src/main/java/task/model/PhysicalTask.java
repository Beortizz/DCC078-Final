package task.model;

import task.memento.PhysicalTaskMemento;
import task.memento.TaskMemento;
import task.strategy.ITaskPriorityStrategy;

import java.time.LocalDate;

public class PhysicalTask extends Task {

    private String address;
    public PhysicalTask(String taskId, String description, String responsiblePerson , String address, LocalDate deadline, int complexity, ITaskPriorityStrategy taskPriorityStrategy) {
        super(taskId, description, responsiblePerson, deadline, complexity, taskPriorityStrategy);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    protected int getWorkDayDuration() {
        return 8;
    }

    @Override
    protected int getWorkWeekDuration() {
        return 6;
    }

    @Override
    public TaskMemento saveToMemento() {
        TaskMemento taskMemento = super.saveToMemento();

        return new PhysicalTaskMemento(taskMemento, this.address);
    }

    @Override
    public void restoreFromMemento(TaskMemento taskMemento) {
        super.restoreFromMemento(taskMemento);
        PhysicalTaskMemento physicalTaskMemento = (PhysicalTaskMemento) taskMemento;
        this.address = physicalTaskMemento.getAddress();
    }


}
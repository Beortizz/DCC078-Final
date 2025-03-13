package task.model;

import task.strategy.ITaskPriorityStrategy;

import java.time.LocalDate;

public class PhysicalTask extends Task {

    private String address;
    public PhysicalTask(String name, String description, String responsiblePerson , String address, LocalDate deadline, int complexity, ITaskPriorityStrategy taskPriorityStrategy) {
        super(name, description, responsiblePerson, deadline, complexity, taskPriorityStrategy);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void printTaskDetails() {
        super.printTaskDetails();
        System.out.println("Endereço de execução: " + address);
    }

    @Override
    protected int getWorkDayDuration() {
        return 8;
    }

    @Override
    protected int getWorkWeekDuration() {
        return 6;
    }


}
package task.model;

import task.strategy.ITaskPriorityStrategy;

import java.time.LocalDate;

public class DigitalTask extends Task {
    private String accessLink;

    public DigitalTask(String taskId, String description, String accessLink, String responsiblePerson, LocalDate deadline, int complexity, ITaskPriorityStrategy taskPriorityStrategy) {
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
    public void printTaskDetails() {
        super.printTaskDetails();
        System.out.println("Link de acesso: " + accessLink); // Adiciona o link de acesso
    }

    @Override
    protected int getWorkDayDuration() {
        return 6;
    }

    @Override
    protected int getWorkWeekDuration() {
        return 5;
    }
}
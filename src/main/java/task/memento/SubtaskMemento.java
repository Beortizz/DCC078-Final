package task.memento;

import task.model.Subtask;

public class SubtaskMemento {
    private final String name;
    private final String description;
    private final int hoursNeeded;
    private final boolean done;

    public SubtaskMemento(String name, String description, int hoursNeeded, boolean done) {
        this.name = name;
        this.description = description;
        this.hoursNeeded = hoursNeeded;
        this.done = done;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getHoursNeeded() {
        return hoursNeeded;
    }

    public boolean isDone() {
        return done;
    }

    public static SubtaskMemento fromSubtask(Subtask subtask) {
        return new SubtaskMemento(subtask.getName(), subtask.getDescription(), subtask.getHoursNeeded(), subtask.isDone());
    }
}

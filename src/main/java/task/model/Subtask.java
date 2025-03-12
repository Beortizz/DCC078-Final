package task.model;

public class Subtask implements ISubtask {
    private String name;
    private String description;
    private int hoursNeeded;

    public Subtask(String name, String description, int hoursNeeded) {
        this.setName(name);
        this.setDescription(description);
        this.setHoursNeeded(hoursNeeded);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name + " (Descrição: " + description + ")";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHoursNeeded() {
        return hoursNeeded;
    }

    public void setHoursNeeded(int hoursNeeded) {
        if(hoursNeeded < 0) {
            throw new IllegalArgumentException("Horas necessárias não podem ser negativas");
        }
        this.hoursNeeded = hoursNeeded;
    }
}
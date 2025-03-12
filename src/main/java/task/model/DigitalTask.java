package task.model;

public class DigitalTask extends Task {
    private String accessLink;

    public DigitalTask(String taskId, String description, String accessLink, String responsiblePerson) {
        super(taskId, description, responsiblePerson);
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
}
package task.model;

public class PhysicalTask extends Task {

    private String address;
    public PhysicalTask(String name, String description, String responsiblePerson , String address) {
        super(name, description, responsiblePerson);
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
}
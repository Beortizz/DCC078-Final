package task.memento;

import task.model.Subtask;
import task.state.TaskState;
import task.strategy.ITaskPriorityStrategy;

import java.time.LocalDate;
import java.util.List;

public class PhysicalTaskMemento extends TaskMemento {
    private String address;

    public PhysicalTaskMemento(TaskMemento taskMemento, String address) {
        super(taskMemento.getTaskId(), taskMemento.getDescription(), taskMemento.getResponsiblePerson(),
                taskMemento.getProgress(), taskMemento.getState(), taskMemento.getTaskPriorityStrategy(), taskMemento.getDeadline(),
                taskMemento.getComplexity(), taskMemento.isApproved(), taskMemento.getSubtasks().stream().map(subtaskMemento ->
                        new Subtask(subtaskMemento.getName(), subtaskMemento.getDescription(), subtaskMemento.getHoursNeeded())
                ).toList());
        this.setAddress(address);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

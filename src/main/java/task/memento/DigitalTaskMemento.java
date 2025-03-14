package task.memento;

import task.model.Subtask;
import task.state.TaskState;
import task.strategy.ITaskPriorityStrategy;

import java.time.LocalDate;
import java.util.List;

public class DigitalTaskMemento extends TaskMemento {
    private String accessLink;

    public DigitalTaskMemento(TaskMemento taskMemento, String accessLink) {
        super(taskMemento.getTaskId(), taskMemento.getDescription(), taskMemento.getResponsiblePerson(),
                taskMemento.getProgress(), taskMemento.getState(), taskMemento.getTaskPriorityStrategy(), taskMemento.getDeadline(),
                taskMemento.getComplexity(), taskMemento.isApproved(), taskMemento.getSubtasks().stream().map(subtaskMemento ->
                        new Subtask(subtaskMemento.getName(), subtaskMemento.getDescription(), subtaskMemento.getHoursNeeded())
                ).toList());
        this.setAccessLink(accessLink);
    }

    public String getAccessLink() {
        return accessLink;
    }

    public void setAccessLink(String accessLink) {
        this.accessLink = accessLink;
    }
}

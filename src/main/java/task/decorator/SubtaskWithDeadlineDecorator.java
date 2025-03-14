package task.decorator;

import task.model.Subtask;

import java.time.LocalDate;

public class SubtaskWithDeadlineDecorator extends SubtaskDecorator {
    private LocalDate deadline;

    public SubtaskWithDeadlineDecorator(Subtask decoratedSubtask, LocalDate deadline) {
        super(decoratedSubtask);
        this.deadline = deadline;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public boolean isDeadlineApproaching() {
        return LocalDate.now().plusDays(2).isAfter(deadline);
    }
}

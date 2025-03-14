package task.chainOfResponsability;

import task.model.Task;

public class Coordinator extends TaskApprover {

    @Override
    protected boolean canApprove(Task task) {
        return task.getTotalHours() <= 20;
    }

    @Override
    protected String getRole() {
        return "Coordinator";
    }
}

package task.chainOfResponsability;

import task.model.Task;

public class Manager extends TaskApprover {

    @Override
    protected boolean canApprove(Task task) {
        return task.getTotalHours() <= 40;
    }

    @Override
    protected String getRole() {
        return "Manager";
    }
}

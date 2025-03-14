package task.chainOfResponsability;

import task.model.Task;

public class Director extends TaskApprover {

    @Override
    protected boolean canApprove(Task task) {
        return true;
    }

    @Override
    protected String getRole() {
        return "Director";
    }
}

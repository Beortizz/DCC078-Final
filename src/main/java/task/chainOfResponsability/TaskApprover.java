package task.chainOfResponsability;

import task.model.Task;

public abstract class TaskApprover {

    protected TaskApprover nextApprover;

    public void setNextApprover(TaskApprover nextApprover) {
        this.nextApprover = nextApprover;
    }

    public void approveTask(Task task) {
        if(task.isDone()) {
            if (canApprove(task)) {
                task.setApproved(true);
            } else {
                if (nextApprover != null) {
                    nextApprover.approveTask(task);
                }
            }
        } else{
            throw new IllegalArgumentException("Tarefa não está concluída");
        }

    }

    protected abstract boolean canApprove(Task task);
    protected abstract String getRole();

}

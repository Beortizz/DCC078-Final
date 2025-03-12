package task.state;

import task.model.Task;

public class TaskOnHoldState extends TaskState {

    private TaskOnHoldState() {
    }

    private static TaskOnHoldState instance = new TaskOnHoldState();

    public static TaskOnHoldState getInstance() {
        return instance;
    }

    @Override
    public String getState() {
        return "Em espera";
    }

    @Override
    public boolean changeToInProgress(Task task) {
        task.setState(TaskInProgressState.getInstance());
        return true;
    }

    @Override
    public boolean changeToDone(Task task) {
        task.setState(TaskDoneState.getInstance());
        return true;
    }
}
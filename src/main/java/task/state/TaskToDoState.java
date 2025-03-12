package task.state;

import task.model.Task;

public class TaskToDoState extends TaskState {

    private TaskToDoState() {
    }

    private static TaskToDoState instance = new TaskToDoState();

    public static TaskToDoState getInstance() {
        return instance;
    }

    @Override
    public String getState() {
        return "A fazer";
    }

    @Override
    public boolean changeToInProgress(Task task) {
        task.setState(TaskInProgressState.getInstance());
        return true;
    }

    @Override
    public boolean changeToOnHold(Task task) {
        task.setState(TaskOnHoldState.getInstance());
        return true;
    }
}
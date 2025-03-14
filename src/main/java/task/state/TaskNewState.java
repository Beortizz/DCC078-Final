package task.state;

import task.model.Task;

public class TaskNewState extends TaskState {

    private TaskNewState() {
    }

    private static TaskNewState instance = new TaskNewState();

    public static TaskNewState getInstance() {
        return instance;
    }

    @Override
    public String getState() {
        return "Nova";
    }

    @Override
    public boolean changeToInProgress(Task task) {
        task.setState(TaskInProgressState.getInstance());
        return true;
    }

    @Override
    public boolean changeToToDo(Task task) {
        task.setState(TaskToDoState.getInstance());
        return true;
    }
    @Override
    public String toString() {
        return "Nova";
    }
}
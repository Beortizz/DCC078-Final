package task.state;

import task.model.Task;

public class TaskDoneState extends TaskState {

    private TaskDoneState() {
    }

    private static TaskDoneState instance = new TaskDoneState();

    public static TaskDoneState getInstance() {
        return instance;
    }

    @Override
    public String getState() {
        return "Finalizada";
    }
}
package task.state;

import task.model.Task;

public class TaskInProgressState extends TaskState {

    // Construtor privado para garantir Singleton
    private TaskInProgressState() {
    }

    // Instância única do estado
    private static TaskInProgressState instance = new TaskInProgressState();

    // Método para obter a instância única
    public static TaskInProgressState getInstance() {
        return instance;
    }

    @Override
    public String getState() {
        return "Em andamento";
    }

    @Override
    public boolean changeToOnHold(Task task) {
        task.setState(TaskOnHoldState.getInstance());
        return true;
    }

    @Override
    public boolean changeToDone(Task task) {
        task.setState(TaskDoneState.getInstance());
        return true;
    }

    @Override
    public boolean changeToToDo(Task task) {
        task.setState(TaskToDoState.getInstance());
        return true;
    }
}
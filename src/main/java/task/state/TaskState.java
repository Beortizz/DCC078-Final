package task.state;

import task.model.Task;

public  abstract class TaskState {

    public abstract String getState();

    public boolean changeToInProgress(Task task){
        return false;
    }
    public boolean changeToDone(Task task){
        return false;
    }
    public boolean changeToNew(Task task){
        return false;
    }
    public boolean changeToToDo(Task task){
        return false;
    }
    public boolean changeToOnHold(Task task){
        return false;
    }
}

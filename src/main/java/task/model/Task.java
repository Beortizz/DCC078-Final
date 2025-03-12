package task.model;

import task.state.TaskState;
import task.state.TaskNewState;
import task.strategy.ITaskPriorityStrategy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Task extends Observable implements ITask {
    private String taskId;
    private String description;
    private String responsiblePerson;
    private List<Subtask> subtasks;
    private double progress; // Porcentagem concluída da tarefa
    private TaskState state;
    private ITaskPriorityStrategy taskPriorityStrategy;
    private LocalDate deadline;

    private int complexity;

    public Task(String taskId, String description, String responsiblePerson, LocalDate deadline, int complexity, ITaskPriorityStrategy taskPriorityStrategy) {
        this.setTaskId(taskId);
        this.setDescription(description);
        this.setSubtasks(new ArrayList<>());
        this.setProgress(0.0);
        this.setResponsiblePerson(responsiblePerson);
        this.setState(TaskNewState.getInstance());
        this.setDeadline(deadline);
        this.setComplexity(complexity);
        this.setTaskPriorityStrategy(taskPriorityStrategy);
    }

    @Override
    public void addSubtask(Subtask subtask) {
        this.subtasks.add(subtask);
        updateProgress(); // Atualiza o progresso ao adicionar uma subtarefa
    }

    @Override
    public void removeSubtask(Subtask subtask) {
        this.subtasks.remove(subtask);
        updateProgress(); // Atualiza o progresso ao remover uma subtarefa
    }

    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setState(TaskState state) {
        this.state = state;
        setChanged();
        notifyObservers(this);
    }

    public TaskState getState() {
        return state;
    }

    public String getStateName() {
        return state.getState();
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        if (deadline.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("O prazo não pode ser uma data no passado.");
        }
        this.deadline = deadline;
    }


    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<Subtask> subtasks) {
        this.subtasks = subtasks;
    }

    public int getComplexity() {
        return complexity;
    }

    public void setComplexity(int complexity) {
        if(complexity < 1 || complexity > 100){
            throw new IllegalArgumentException("Complexidade deve ser um valor entre 1 e 100");
        }
        this.complexity = complexity;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public double getHoursNeeded() {
        return subtasks.stream()
                .mapToDouble(Subtask::getHoursNeeded)
                .sum();
    }

    @Override
    public void printTaskDetails() {
        System.out.println("Tarefa ID: " + taskId);
        System.out.println("Descrição: " + description);
        System.out.println("Subtarefas:");
        for (Subtask subtask : subtasks) {
            System.out.println("- " + subtask);
        }
        System.out.println("Progresso: " + progress + "%");
        System.out.println("Status: " + state.getState());
    }

    private void updateProgress() {
        if (subtasks.isEmpty()) {
            this.progress = 0.0;
        } else {
            this.progress = (subtasks.size() / 10.0) * 100;
        }
    }

    public double getProgress() {
        return progress;
    }

    public boolean changeToInProgress() {
        return state.changeToInProgress(this);
    }

    public boolean changeToDone() {
        return state.changeToDone(this);
    }

    public boolean changeToOnHold() {
        return state.changeToOnHold(this);
    }

    public boolean changeToToDo() {
        return state.changeToToDo(this);
    }

    public int calculatePriority() {
        return taskPriorityStrategy.calculatePriority(this);
    }

    public void setTaskPriorityStrategy(ITaskPriorityStrategy taskPriorityStrategy) {
        this.taskPriorityStrategy = taskPriorityStrategy;
    }
}
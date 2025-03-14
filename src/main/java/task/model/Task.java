package task.model;

import task.memento.SubtaskMemento;
import task.memento.TaskMemento;
import task.state.TaskState;
import task.state.TaskNewState;
import task.strategy.ITaskPriorityStrategy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public abstract class Task extends Observable implements ITask {
    private String taskId;
    private String description;
    private String responsiblePerson;
    private List<Subtask> subtasks;
    private double progress; // Porcentagem concluída da tarefa
    private TaskState state;

    public ITaskPriorityStrategy getTaskPriorityStrategy() {
        return taskPriorityStrategy;
    }

    private ITaskPriorityStrategy taskPriorityStrategy;
    private LocalDate deadline;
    private int complexity;
    private boolean approved;

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
        this.setApproved(false);
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
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

    private void updateProgress() {
        if (subtasks.isEmpty()) {
            this.progress = 0.0;
        } else {
            long completedSubtasks = subtasks.stream().filter(Subtask::isDone).count();
            this.progress = (completedSubtasks / (double) subtasks.size()) * 100;
        }
    }

    public void completeSubtask(Subtask subtask) {
        subtask.setDone(true);
        updateProgress();
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

    protected abstract int getWorkDayDuration();
    protected abstract int getWorkWeekDuration();

    public double calculateWorkDaysNeeded() {
        int totalHours = 0;
        for (Subtask subtask : subtasks) {
            totalHours += subtask.getHoursNeeded();
        }
        return totalHours / (double) getWorkDayDuration();
    }

    public double calculateWorkWeeksNeeded() {
        int totalHours = 0;
        for (Subtask subtask : subtasks) {
            totalHours += subtask.getHoursNeeded();
        }
        return totalHours / (double) (getWorkDayDuration() * getWorkWeekDuration());
    }

    public List<Double> calculateWorkTime() {
        double weekDays = calculateWorkDaysNeeded();
        double workWeeks = calculateWorkWeeksNeeded();
        List<Double> workTime = new ArrayList<>();
        workTime.add(weekDays);
        workTime.add(workWeeks);

        return workTime;
    }

    public int getTotalHours() {
        return subtasks.stream()
                .mapToInt(Subtask::getHoursNeeded)
                .sum();
    }

    public boolean isDone() {
        return subtasks.stream().allMatch(Subtask::isDone);
    }

    public TaskMemento saveToMemento() {
        return new TaskMemento(
                taskId, description, responsiblePerson, progress, state,
                taskPriorityStrategy, deadline, complexity, approved, subtasks
        );
    }
    public void restoreFromMemento(TaskMemento memento) {
        this.taskId = memento.getTaskId();
        this.description = memento.getDescription();
        this.responsiblePerson = memento.getResponsiblePerson();
        this.progress = memento.getProgress();
        this.state = memento.getState();
        this.taskPriorityStrategy = memento.getTaskPriorityStrategy();
        this.deadline = memento.getDeadline();
        this.complexity = memento.getComplexity();
        this.approved = memento.isApproved();
        this.subtasks = covertMementoToSubtask(memento.getSubtasks());
    }

    private List<Subtask> covertMementoToSubtask(List<SubtaskMemento> subtaskMementos){
        List<Subtask> subtasks = new ArrayList<>();
        for(SubtaskMemento subtaskMemento : subtaskMementos){
            Subtask subtask = new Subtask(subtaskMemento.getName(), subtaskMemento.getDescription(), subtaskMemento.getHoursNeeded());
            subtask.restoreFromMemento(subtaskMemento);
            subtasks.add(subtask);
        }
        return subtasks;
    }
}
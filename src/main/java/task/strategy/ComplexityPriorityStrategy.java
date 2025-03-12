package task.strategy;

import task.model.Task;

public class ComplexityPriorityStrategy implements ITaskPriorityStrategy {
    @Override
    public int calculatePriority(Task task) {
        return task.getComplexity(); // Quanto maior complexidade, maior prioridade
    }
}
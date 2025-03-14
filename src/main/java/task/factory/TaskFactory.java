package task.factory;

import task.model.Task;
import task.strategy.ITaskPriorityStrategy;

import java.time.LocalDate;

public class TaskFactory {

    public static Task createTask(String taskType, String taskId, String description, String responsiblePerson, LocalDate deadline, int complexity, ITaskPriorityStrategy taskPriorityStrategy, String ...optionalArgs) {
        Class<?> targetClass = null;
        Object object = null;

        try {

            targetClass = Class.forName("task.model." + taskType + "Task");
            object = targetClass.getDeclaredConstructor(String.class, String.class, String.class, String.class, LocalDate.class, int.class, ITaskPriorityStrategy.class)
                    .newInstance(taskId, description, responsiblePerson,optionalArgs[0], deadline, complexity, taskPriorityStrategy);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Tipo de tarefa inexistente: " + taskType);
        }

        if (!(object instanceof Task)) {
            throw new IllegalArgumentException("Tipo de tarefa inválido: " + taskType);
        }

        return (Task) object;
    }
}
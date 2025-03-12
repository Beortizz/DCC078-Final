package task.factory;

import task.model.Task;

public class TaskFactory {

    public static Task createTask(String taskType, String taskId, String description, String additionalInfo) {
        Class<?> targetClass = null;
        Object object = null;

        try {

            targetClass = Class.forName("task.model." + taskType + "Task");
            object = targetClass.getDeclaredConstructor(String.class, String.class, String.class)
                    .newInstance(taskId, description, additionalInfo);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Tipo de tarefa inexistente: " + taskType);
        }

        if (!(object instanceof Task)) {
            throw new IllegalArgumentException("Tipo de tarefa inválido: " + taskType);
        }

        return (Task) object;
    }
}
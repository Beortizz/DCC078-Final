package task.factory;

import org.junit.jupiter.api.Test;
import task.model.Task;
import task.strategy.ITaskPriorityStrategy;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TaskFactoryTest {

    @Test
    void createPhysicalTask() {
        // Arrange
        String taskType = "Physical";
        String taskId = "1";
        String description = "description";
        String responsiblePerson = "responsiblePerson";
        String optionalArg = "address";
        int complexity = 1;
        ITaskPriorityStrategy taskPriorityStrategy = null;

        Task task = TaskFactory.createTask(taskType, taskId, description, responsiblePerson, LocalDate.of(2025, 04, 30), complexity, taskPriorityStrategy, optionalArg);


        assertNotNull(task);
        assertEquals(taskId, task.getTaskId());
        assertEquals(description, task.getDescription());
        assertEquals(responsiblePerson, task.getResponsiblePerson());
        assertEquals(complexity, task.getComplexity());
    }

    @Test
    void createTaskWithInvalidType() {
        // Arrange
        String taskType = "InvalidType";
        String taskId = "1";
        String description = "description";
        String responsiblePerson = "responsiblePerson";
        String optionalArg = "address";
        int complexity = 1;
        ITaskPriorityStrategy taskPriorityStrategy = null;

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> TaskFactory.createTask(taskType, taskId, description, responsiblePerson, null, complexity, taskPriorityStrategy, optionalArg));
    }

    @Test
    void createTaskWithInvalidObject() {
        // Arrange
        String taskType = "Physical";
        String taskId = "1";
        String description = "description";
        String responsiblePerson = "responsiblePerson";
        String optionalArg = "address";
        int complexity = 1;
        ITaskPriorityStrategy taskPriorityStrategy = null;

        assertThrows(IllegalArgumentException.class, () -> TaskFactory.createTask(taskType, taskId, description, responsiblePerson, null, complexity, taskPriorityStrategy, optionalArg));
    }

    @Test
    void createDigitalTask(){
        String taskType = "Digital";
        String taskId = "1";
        String description = "description";
        String responsiblePerson = "responsiblePerson";
        String optionalArg = "accessLink";
        int complexity = 1;
        ITaskPriorityStrategy taskPriorityStrategy = null;

        Task task = TaskFactory.createTask(taskType, taskId, description, responsiblePerson, LocalDate.of(2025, 04, 30), complexity, taskPriorityStrategy, optionalArg);

        assertNotNull(task);
        assertEquals(taskId, task.getTaskId());
        assertEquals(description, task.getDescription());
        assertEquals(responsiblePerson, task.getResponsiblePerson());
        assertEquals(complexity, task.getComplexity());
    }

}
package task.decorator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import task.model.DigitalTask;
import task.model.ITask;
import task.model.Subtask;
import task.strategy.DeadlinePriorityStrategy;
import task.strategy.ITaskPriorityStrategy;

import java.time.LocalDate;

class UrgentTaskDecoratorTest {

    private UrgentTaskDecorator urgentTaskDecorator;
    private ITask ITask;

    @BeforeEach
    void setUp() {
        ITaskPriorityStrategy taskPriorityStrategy = new DeadlinePriorityStrategy();
        DigitalTask digitalTask = new DigitalTask("1", "description", "responsiblePerson", "accessLink", LocalDate.now(), 1, taskPriorityStrategy);
        urgentTaskDecorator = new UrgentTaskDecorator(digitalTask);
    }

    @Test
    void addSubtask() {
        Subtask subtask = new Subtask("1", "description", 3);
        urgentTaskDecorator.addSubtask(subtask);
        assertEquals(1, urgentTaskDecorator.getSubtasks().size());
    }

    @Test
    void removeSubtask() {
        Subtask subtask = new Subtask("1", "description", 3);
        urgentTaskDecorator.addSubtask(subtask);
        urgentTaskDecorator.removeSubtask(subtask);
        assertEquals(0, urgentTaskDecorator.getSubtasks().size());
    }

    @Test
    void calculatePriority() {
        assertEquals(200, urgentTaskDecorator.calculatePriority());
    }

    @Test
    void getSubtasks() {
        urgentTaskDecorator.addSubtask(new Subtask("1", "description", 3));
        assertNotNull(urgentTaskDecorator.getSubtasks());
    }

    @Test
    void setSubtasks() {
        urgentTaskDecorator.setSubtasks(null);
        assertNull(urgentTaskDecorator.getSubtasks());
    }
}
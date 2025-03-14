package task.chainOfResponsability;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.model.DigitalTask;
import task.model.Task;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CoordinatorTest {


    private Task task;
    private Coordinator coordinator;

    @BeforeEach
    void setUp() {
        task = new DigitalTask("1", "description", "responsiblePerson","accessLink", LocalDate.of(2025,03,30), 1, null);
        coordinator = new Coordinator();
    }


    @Test
    void canApprove() {
        task.addSubtask(new task.model.Subtask("1", "Test subtask", 15));
        task.getSubtasks().get(0).setDone(true);
        assertTrue(coordinator.canApprove(task));
    }

    @Test
    void cannotApprove() {
        task.addSubtask(new task.model.Subtask("1", "Test subtask", 100));
        task.getSubtasks().get(0).setDone(true);
        assertFalse(coordinator.canApprove(task));
    }

    @Test
    void testExceptionWhenTaskNotDone() {
        task.addSubtask(new task.model.Subtask("1", "Test subtask", 15));
        assertThrows(IllegalArgumentException.class, () -> coordinator.approveTask(task));
    }

    @Test
    void getRole() {
        assertEquals("Coordinator", coordinator.getRole());
    }
}
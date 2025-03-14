package task.chainOfResponsability;

import task.model.DigitalTask;
import task.model.Subtask;
import task.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {

    private Task task;
    private Manager manager;
    private Coordinator coordinator;
    private Director director;

    @BeforeEach
    void setUp() {
        task = new DigitalTask("1", "description", "responsiblePerson","accessLink", LocalDate.now(), 1, null);
        manager = new Manager();
    }

    @Test
    void testManagerApprovesTask() {
        task.addSubtask(new Subtask("1", "Test subtask", 29));
        task.getSubtasks().get(0).setDone(true);
        assertTrue(manager.canApprove(task));
    }

    @Test
    void testManagerCannotApproveTask() {
        task.addSubtask(new Subtask("1", "Test subtask", 100));
        task.getSubtasks().get(0).setDone(true);
        assertFalse(manager.canApprove(task));
    }

    @Test
    void testExceptionWhenTaskNotDone() {
        task.addSubtask(new Subtask("1", "Test subtask", 29));
        assertThrows(IllegalArgumentException.class, () -> manager.approveTask(task));
    }

    @Test
    void testGetRole() {
        assertEquals("Manager", manager.getRole());
    }
}

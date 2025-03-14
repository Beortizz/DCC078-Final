package task.chainOfResponsability;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.model.DigitalTask;
import task.model.Task;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DirectorTest {

    private Task task;
    private Manager manager;
    private Coordinator coordinator;
    private Director director;

    @BeforeEach
    void setUp() {
        task = new DigitalTask("1", "description", "responsiblePerson","accessLink", LocalDate.now(), 1, null);
        manager = new Manager();
        coordinator = new Coordinator();
        director = new Director();

        manager.setNextApprover(coordinator);
        coordinator.setNextApprover(director);
    }

    @Test
    void testDirectorCanApprove() {
        task.addSubtask(new task.model.Subtask("1", "Test subtask", 29));
        task.getSubtasks().get(0).setDone(true);
        assertTrue(director.canApprove(task));
    }

    @Test
    void testDirectorCannotApproveTaskNotDone() {
        task.addSubtask(new task.model.Subtask("1", "Test subtask", 100));
        assertThrows(IllegalArgumentException.class, () -> director.approveTask(task));
    }
    @Test
    void getRole() {
        assertEquals("Director", director.getRole());
    }
}
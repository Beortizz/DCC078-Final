package task.chainOfResponsability;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.model.DigitalTask;
import task.model.Subtask;
import task.model.Task;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TaskApproverTest {

    private Task task;
    private Manager manager;
    private Coordinator coordinator;
    private Director director;

    @BeforeEach
    void setUp() {
        task = new DigitalTask("1", "description", "responsiblePerson", "accessLink", LocalDate.now(), 1, null);
        manager = new Manager();
        coordinator = new Coordinator();
        director = new Director();

        coordinator.setNextApprover(manager);
        manager.setNextApprover(director);
    }

    @Test
    void testCoordinatorApprovesTask() {
        task.addSubtask(new Subtask("1", "Test subtask", 15));
        task.getSubtasks().get(0).setDone(true);

        coordinator.approveTask(task);

        assertTrue(task.isApproved());
    }

    @Test
    void testManagerApprovesTask() {
        task.addSubtask(new Subtask("1", "Test subtask", 30));
        task.getSubtasks().get(0).setDone(true);

        coordinator.approveTask(task);

        assertTrue(task.isApproved());
    }

    @Test
    void testDirectorApprovesTask() {
        task.addSubtask(new Subtask("1", "Test subtask", 50));
        task.getSubtasks().get(0).setDone(true);

        coordinator.approveTask(task);

        assertTrue(task.isApproved());
    }
    @Test
    void testTaskNotApprovedIfSubtasksNotDone() {

        task.addSubtask(new Subtask("1", "Test subtask", 30));

        assertThrows(IllegalArgumentException.class, () -> coordinator.approveTask(task));
    }

    @Test
    void testTaskApprovalChain() {
        task.addSubtask(new Subtask("1", "Test subtask", 30));
        task.getSubtasks().get(0).setDone(true);

        coordinator.approveTask(task);
        assertTrue(task.isApproved());

        task = new DigitalTask("2", "description", "responsiblePerson", "accessLink", LocalDate.now(), 1, null);
        task.addSubtask(new Subtask("1", "Test subtask", 10));
        task.getSubtasks().get(0).setDone(true);

        coordinator.approveTask(task);
        assertTrue(task.isApproved());

        task = new DigitalTask("3", "description", "responsiblePerson", "accessLink", LocalDate.now(), 1, null);
        task.addSubtask(new Subtask("1", "Test subtask", 50));
        task.getSubtasks().get(0).setDone(true);

        coordinator.approveTask(task);
        assertTrue(task.isApproved());
    }

}

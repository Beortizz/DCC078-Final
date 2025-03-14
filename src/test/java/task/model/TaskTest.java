package task.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import task.factory.TaskFactory;
import task.state.TaskNewState;
import task.strategy.ComplexityPriorityStrategy;
import task.strategy.DeadlinePriorityStrategy;
import task.strategy.ITaskPriorityStrategy;

class TaskTest {

    private Task physicalTask;
    private Task digitalTask;
    private Subtask subtask;
    private ITaskPriorityStrategy taskPriorityStrategy;

    @BeforeEach
    void createPhysicalTask() {
        physicalTask = TaskFactory.createTask("Physical", "1", "Physical Task", "John Doe", LocalDate.of(2025,03,31), 50, taskPriorityStrategy, "main street");
    }
    @BeforeEach
    void createDigitalTask() {
       digitalTask = TaskFactory.createTask("Digital", "1", "Digital Task", "John Doe", LocalDate.of(2025,03,31), 50, taskPriorityStrategy, "www.google.com");
    }


    @Test
    void testphysicalTaskInitialization() {
        assertEquals("1", physicalTask.getTaskId());
        assertEquals("Physical Task", physicalTask.getDescription());
        assertEquals("main street", ((PhysicalTask) physicalTask).getAddress());
        assertEquals("John Doe", physicalTask.getResponsiblePerson());
        assertEquals("Nova", physicalTask.getState().toString());
        assertEquals(LocalDate.of(2025,03,31), physicalTask.getDeadline());
        assertEquals(50, physicalTask.getComplexity());
        assertEquals(0, physicalTask.getProgress());
        assertFalse(physicalTask.isApproved());
        assertEquals(0, physicalTask.getSubtasks().size());
    }
    @Test
    void testAddSubtask() {
        Subtask subtask = new Subtask("1", "Subtask",5);
        physicalTask.addSubtask(subtask);
        assertEquals(1, physicalTask.getSubtasks().size());
        assertEquals(subtask, physicalTask.getSubtasks().get(0));
    }
    @Test
    void testRemoveSubtask() {
        Subtask subtask = new Subtask("1", "Subtask",5);
        physicalTask.addSubtask(subtask);
        physicalTask.removeSubtask(subtask);
        assertEquals(0, physicalTask.getSubtasks().size());
    }
    @Test
    void testCompleteSubtask() {
        Subtask subtask = new Subtask("1", "Subtask",5);
        physicalTask.addSubtask(subtask);
        physicalTask.completeSubtask(subtask);
        assertTrue(subtask.isDone());
    }

    @Test
    void testdigitalTaskInitialization() {
        assertEquals("1", digitalTask.getTaskId());
        assertEquals("Digital Task", digitalTask.getDescription());
        assertEquals("www.google.com", ((DigitalTask) digitalTask).getAccessLink());
        assertEquals("John Doe", digitalTask.getResponsiblePerson());
        assertEquals("Nova", digitalTask.getState().toString());
        assertEquals(LocalDate.of(2025,03,31), digitalTask.getDeadline());
        assertEquals(50, digitalTask.getComplexity());
        assertEquals(0, digitalTask.getProgress());
        assertFalse(digitalTask.isApproved());
        assertEquals(0, digitalTask.getSubtasks().size());
    }

    @Test
    void testIsNotDone() {
        physicalTask.addSubtask(new Subtask("1", "Subtask",5));
        assertFalse(physicalTask.isDone());
    }
    @Test
    void testIsDone() {
        Subtask subtask = new Subtask("1", "Subtask",5);
        physicalTask.addSubtask(subtask);
        physicalTask.completeSubtask(subtask);
        assertTrue(physicalTask.isDone());
    }
    @Test
    void testGetHoursNeeded() {
        Subtask subtask = new Subtask("1", "Subtask",5);
        Subtask subtask2 = new Subtask("2", "Subtask 2",5);
        physicalTask.addSubtask(subtask);
        physicalTask.addSubtask(subtask2);
        assertEquals(10, physicalTask.getHoursNeeded());
    }
    @Test
    void testUpdateProgressWhenAddingSubtask() {
        Subtask subtask = new Subtask("1", "Subtask",5);
        physicalTask.addSubtask(subtask);
        assertEquals(0, physicalTask.getProgress());
    }
    @Test
    void testUpdateProgressWhenRemovingSubtask() {
        Subtask subtask = new Subtask("1", "Subtask",5);
        physicalTask.addSubtask(subtask);
        physicalTask.removeSubtask(subtask);
        assertEquals(0, physicalTask.getProgress());
    }
    @Test
    void testUpdateProgressWhenCompletingSubtask() {
        Subtask subtask = new Subtask("1", "Subtask",5);
        physicalTask.addSubtask(subtask);
        physicalTask.completeSubtask(subtask);
        assertEquals(100, physicalTask.getProgress());
    }
    @Test
    void testUpdateProgressWhenCompletingSubtaskWithOtherSubtasks() {
        Subtask subtask1 = new Subtask("1", "Subtask 1",5);
        Subtask subtask2 = new Subtask("2", "Subtask 2",5);
        physicalTask.addSubtask(subtask1);
        physicalTask.addSubtask(subtask2);
        physicalTask.completeSubtask(subtask1);
        assertEquals(50, physicalTask.getProgress());
    }
    @Test
    void testUpdateProgressWhenCompletingSubtaskWithOtherSubtasksAndAlreadyCompletedSubtask() {
        Subtask subtask1 = new Subtask("1", "Subtask 1",5);
        Subtask subtask2 = new Subtask("2", "Subtask 2",5);
        physicalTask.addSubtask(subtask1);
        physicalTask.addSubtask(subtask2);
        physicalTask.completeSubtask(subtask1);
        physicalTask.completeSubtask(subtask2);
        assertEquals(100, physicalTask.getProgress());
    }
    @Test
    void testUpdateProgressWhenRemovingSubtaskWithOtherSubtasks() {
        Subtask subtask1 = new Subtask("1", "Subtask 1",5);
        Subtask subtask2 = new Subtask("2", "Subtask 2",5);
        physicalTask.addSubtask(subtask1);
        physicalTask.addSubtask(subtask2);
        physicalTask.removeSubtask(subtask1);
        assertEquals(0, physicalTask.getProgress());
    }
    @Test
    void testUpdateProgressWhenRemovingSubtaskWithAnotherSubtaskCompleted() {
        Subtask subtask1 = new Subtask("1", "Subtask 1", 5);
        Subtask subtask2 = new Subtask("2", "Subtask 2", 5);
        physicalTask.addSubtask(subtask1);
        physicalTask.addSubtask(subtask2);
        physicalTask.completeSubtask(subtask2);
        physicalTask.removeSubtask(subtask1);
        assertEquals(100, physicalTask.getProgress());
    }
    // Testes de mudança de estado
    @Test
    void testChangeToInProgressWhenNew() {
        assertTrue(digitalTask.changeToInProgress());
    }
    @Test
    void testChangeToToDoWhenNew() {
        assertTrue(digitalTask.changeToToDo());
    }
    @Test
    void testChangeToDoneWhenNew() {
        assertFalse(digitalTask.changeToDone());
    }
    @Test
    void testChangeToOnHoldWhenNew() {
        assertFalse(digitalTask.changeToOnHold());
    }
    @Test
    void testChangeToInProgressWhenToDo() {
        digitalTask.changeToToDo();
        assertTrue(digitalTask.changeToInProgress());
    }

    @Test
    void testChangeToOnHoldWhenToDo() {
        digitalTask.changeToToDo();
        assertTrue(digitalTask.changeToOnHold());
    }
    @Test
    void testChangeToDoneWhenToDo() {
        digitalTask.changeToToDo();
        assertFalse(digitalTask.changeToDone());
    }
    @Test
    void testChangeToInProgressWhenOnHold() {
        digitalTask.changeToToDo();
        digitalTask.changeToOnHold();
        assertTrue(digitalTask.changeToInProgress());
    }
    @Test
    void testChangeToDoneWhenOnHold() {
        digitalTask.changeToToDo();
        digitalTask.changeToOnHold();
        assertTrue(digitalTask.changeToDone());
    }
    @Test
    void testChangeToToDoWhenOnHold() {
        digitalTask.changeToToDo();
        digitalTask.changeToOnHold();
        assertFalse(digitalTask.changeToToDo());
    }
    @Test
    void testChangeToDoneWhenInProgress() {
        digitalTask.changeToToDo();
        digitalTask.changeToInProgress();
        assertTrue(digitalTask.changeToDone());
    }
    @Test
    void testChangeToOnHoldWhenInProgress() {
        digitalTask.changeToToDo();
        digitalTask.changeToInProgress();
        assertEquals("Em andamento", digitalTask.getStateName());
        assertTrue(digitalTask.changeToOnHold());
    }
    @Test
    void testChangeToToDoWhenInProgress() {
        digitalTask.changeToToDo();
        digitalTask.changeToInProgress();
        assertTrue(digitalTask.changeToToDo());
    }

    @Test
    void testChangeToInProgressWhenDone() {
        digitalTask.changeToToDo();
        digitalTask.changeToInProgress();
        digitalTask.changeToDone();
        assertFalse(digitalTask.changeToInProgress());
    }
    @Test
    void testChangeToOnHoldWhenDone() {
        digitalTask.changeToToDo();
        digitalTask.changeToInProgress();
        digitalTask.changeToDone();
        assertFalse(digitalTask.changeToOnHold());
    }

    @Test
    void testChangeToToDoWhenDone() {
        digitalTask.changeToToDo();
        digitalTask.changeToInProgress();
        digitalTask.changeToDone();
        assertFalse(digitalTask.changeToToDo());
    }
    // Fim dos testes de mudança de estado

    //Teste padrão Strategy
    @Test
    void testDeadlinePriorityStrategy() {
        taskPriorityStrategy = new DeadlinePriorityStrategy();

        physicalTask.setTaskPriorityStrategy(taskPriorityStrategy);
        assertEquals(82, physicalTask.calculatePriority());
    }
    @Test
    void testComplexityPriorityStrategy() {
        taskPriorityStrategy = new ComplexityPriorityStrategy();
        physicalTask.setTaskPriorityStrategy(taskPriorityStrategy);
        assertEquals(50, physicalTask.calculatePriority());
    }

    @Test
    void testCalculateWorkDaysNeededForPhysicalTask() {
        Subtask subtask1 = new Subtask("1", "Subtask 1", 10);
        Subtask subtask2 = new Subtask("2", "Subtask 2", 10);
        physicalTask.addSubtask(subtask1);
        physicalTask.addSubtask(subtask2);
        assertEquals(2.5, physicalTask.calculateWorkDaysNeeded());
    }

    @Test
    void testCalculateWorkWeeksNeededForPhysicalTask() {
        Subtask subtask1 = new Subtask("1", "Subtask 1", 10);
        Subtask subtask2 = new Subtask("2", "Subtask 2", 10);
        Subtask subtask3 = new Subtask("3", "Subtask 3", 28);
        physicalTask.addSubtask(subtask1);
        physicalTask.addSubtask(subtask2);
        physicalTask.addSubtask(subtask3);
        assertEquals(1, physicalTask.calculateWorkWeeksNeeded());
    }
    @Test
    void testCalculateWorkTimeForPhysicalTask() {
        Subtask subtask1 = new Subtask("1", "Subtask 1", 10);
        Subtask subtask2 = new Subtask("2", "Subtask 2", 10);
        Subtask subtask3 = new Subtask("3", "Subtask 3", 28);
        physicalTask.addSubtask(subtask1);
        physicalTask.addSubtask(subtask2);
        physicalTask.addSubtask(subtask3);
        assertEquals(Arrays.asList(6.0, 1.0), physicalTask.calculateWorkTime());
    }

    @Test
    void testCalculateWorkDaysNeededForDigitalTask() {
        Subtask subtask1 = new Subtask("1", "Subtask 1", 6);
        Subtask subtask2 = new Subtask("2", "Subtask 2", 12);
        digitalTask.addSubtask(subtask1);
        digitalTask.addSubtask(subtask2);
        assertEquals(3, digitalTask.calculateWorkDaysNeeded());
    }

    @Test
    void testCalculateWorkWeeksNeededForDigitalTask() {
        Subtask subtask1 = new Subtask("1", "Subtask 1", 12);
        Subtask subtask2 = new Subtask("2", "Subtask 2", 12);
        digitalTask.addSubtask(subtask1);
        digitalTask.addSubtask(subtask2);
        assertEquals(0.8, digitalTask.calculateWorkWeeksNeeded());
    }

    @Test
    void testCalculateWorkTimeForDigitalTask() {
        Subtask subtask1 = new Subtask("1", "Subtask 1", 12);
        Subtask subtask2 = new Subtask("2", "Subtask 2", 12);
        digitalTask.addSubtask(subtask1);
        digitalTask.addSubtask(subtask2);
        assertEquals(Arrays.asList(4.0, 0.8), digitalTask.calculateWorkTime());
    }


    @Test
    void setResponsiblePerson() {
        physicalTask.setResponsiblePerson("Jane Doe");
        assertEquals("Jane Doe", physicalTask.getResponsiblePerson());
    }

    @Test
    void getResponsiblePerson() {
        assertEquals("John Doe", physicalTask.getResponsiblePerson());
    }

    @Test
    void setState() {
        physicalTask.setState(TaskNewState.getInstance());
        assertEquals("Nova", physicalTask.getStateName());
    }

    @Test
    void getState() {
        assertEquals("Nova", physicalTask.getStateName());
    }

    @Test
    void getStateName() {
        assertEquals("Nova", physicalTask.getStateName());
    }

    @Test
    void getTaskId() {
        assertEquals("1", physicalTask.getTaskId());
    }

    @Test
    void setTaskId() {
        physicalTask.setTaskId("2");
        assertEquals("2", physicalTask.getTaskId());
    }

    @Test
    void getDescription() {
        assertEquals("Physical Task", physicalTask.getDescription());
    }

    @Test
    void setDescription() {
        physicalTask.setDescription("New Physical Task");
        assertEquals("New Physical Task", physicalTask.getDescription());
    }

    @Test
    void getDeadline() {
        assertEquals(LocalDate.of(2025,03,31), physicalTask.getDeadline());
    }

    @Test
    void setDeadline() {
        physicalTask.setDeadline(LocalDate.of(2025,04,30));
        assertEquals(LocalDate.of(2025,04,30), physicalTask.getDeadline());
    }
    @Test
    void setDeadlineBeforeNow() {
        assertThrows(IllegalArgumentException.class, () -> {
            physicalTask.setDeadline(LocalDate.of(2020,04,30));
        });
    }

    @Test
    void getSubtasks() {
        physicalTask.addSubtask(new Subtask("1", "Subtask",5));
        assertEquals(1, physicalTask.getSubtasks().size());
    }

    @Test
    void setSubtasks() {
        physicalTask.setSubtasks(Arrays.asList(new Subtask("1", "Subtask",5), new Subtask("2", "Subtask 2",5)));
        assertEquals(2, physicalTask.getSubtasks().size());
    }

    @Test
    void getComplexity() {
        assertEquals(50, physicalTask.getComplexity());
    }

    @Test
    void setComplexity() {
        physicalTask.setComplexity(100);
        assertEquals(100, physicalTask.getComplexity());
    }
    @Test
    void setComplexityBelowZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            physicalTask.setComplexity(-1);
        });
    }
    @Test
    void setComplexityAboveHundred() {
        assertThrows(IllegalArgumentException.class, () -> {
            physicalTask.setComplexity(101);
        });
    }

    @Test
    void setProgress() {
        physicalTask.setProgress(50);
        assertEquals(50, physicalTask.getProgress());
    }

    @Test
    void getHoursNeeded() {
        Subtask subtask1 = new Subtask("1", "Subtask 1", 5);
        Subtask subtask2 = new Subtask("2", "Subtask 2", 5);
        physicalTask.addSubtask(subtask1);
        physicalTask.addSubtask(subtask2);
        assertEquals(10, physicalTask.getHoursNeeded());
    }


    @Test
    void getProgress() {
        assertEquals(0, physicalTask.getProgress());
    }

    @Test
    void setTaskPriorityStrategy() {
        physicalTask.setTaskPriorityStrategy(new DeadlinePriorityStrategy());
        assertEquals("DeadlinePriorityStrategy", physicalTask.getTaskPriorityStrategy().getClass().getSimpleName());
    }

    @Test
    void getTaskPriorityStrategy() {
        physicalTask.setTaskPriorityStrategy(new DeadlinePriorityStrategy());
        assertEquals("DeadlinePriorityStrategy", physicalTask.getTaskPriorityStrategy().getClass().getSimpleName());
    }



}
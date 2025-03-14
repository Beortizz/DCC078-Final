package task.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.factory.TaskFactory;
import task.memento.TaskMemento;
import task.strategy.ITaskPriorityStrategy;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DigitalTaskTest {

    Task Task;
    private DigitalTask digitalTask;
    ITaskPriorityStrategy taskPriorityStrategy;

    @BeforeEach
    void setUp() {
        Task = TaskFactory.createTask("Digital", "1", "description", "responsiblePerson", LocalDate.now(), 1, taskPriorityStrategy, "accessLink");

        if(Task instanceof DigitalTask){
            digitalTask = (DigitalTask) Task;
        }
    }
    @Test
    void getAccessLink() {
        assertEquals("accessLink", digitalTask.getAccessLink());
    }

    @Test
    void setAccessLink() {
        digitalTask.setAccessLink("newAccessLink");
        assertEquals("newAccessLink", digitalTask.getAccessLink());
    }

    @Test
    void getWorkDayDuration() {
        assertEquals(6, digitalTask.getWorkDayDuration());
    }

    @Test
    void getWorkWeekDuration() {
        assertEquals(5, digitalTask.getWorkWeekDuration());
    }

    @Test
    void saveToMemento() {
        TaskMemento taskMemento = digitalTask.saveToMemento();
        assertEquals(digitalTask.getTaskId(), taskMemento.getTaskId());
        assertEquals(digitalTask.getDescription(), taskMemento.getDescription());
        assertEquals(digitalTask.getResponsiblePerson(), taskMemento.getResponsiblePerson());
        assertEquals(digitalTask.getProgress(), taskMemento.getProgress());
        assertEquals(digitalTask.getState(), taskMemento.getState());
        assertEquals(digitalTask.getTaskPriorityStrategy(), taskMemento.getTaskPriorityStrategy());
        assertEquals(digitalTask.getDeadline(), taskMemento.getDeadline());
    }

    @Test
    void restoreFromMemento() {
        TaskMemento taskMemento = digitalTask.saveToMemento();
        DigitalTask newDigitalTask = new DigitalTask("2", "newDescription", "newResponsiblePerson", "newAccessLink", LocalDate.now(), 2, taskPriorityStrategy);
        newDigitalTask.restoreFromMemento(taskMemento);
        assertEquals(digitalTask.getTaskId(), newDigitalTask.getTaskId());
        assertEquals(digitalTask.getDescription(), newDigitalTask.getDescription());
        assertEquals(digitalTask.getResponsiblePerson(), newDigitalTask.getResponsiblePerson());
        assertEquals(digitalTask.getProgress(), newDigitalTask.getProgress());
        assertEquals(digitalTask.getState(), newDigitalTask.getState());
        assertEquals(digitalTask.getTaskPriorityStrategy(), newDigitalTask.getTaskPriorityStrategy());
        assertEquals(digitalTask.getDeadline(), newDigitalTask.getDeadline());
        assertEquals(digitalTask.getComplexity(), newDigitalTask.getComplexity());
        assertEquals(digitalTask.isApproved(), newDigitalTask.isApproved());
        assertEquals(digitalTask.getSubtasks().size(), newDigitalTask.getSubtasks().size());
        assertEquals(digitalTask.getAccessLink(), ((DigitalTask) newDigitalTask).getAccessLink());
    }
}
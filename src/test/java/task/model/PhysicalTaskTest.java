package task.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import task.factory.TaskFactory;
import task.memento.PhysicalTaskMemento;
import task.memento.TaskMemento;
import task.strategy.ITaskPriorityStrategy;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PhysicalTaskTest {
    Task Task;
    private PhysicalTask physicalTask;
    ITaskPriorityStrategy taskPriorityStrategy;


    @BeforeEach
    void setUp() {
      Task = TaskFactory.createTask("Physical", "1", "description", "responsiblePerson", LocalDate.now(), 1, taskPriorityStrategy, "address");

      if(Task instanceof PhysicalTask){
        physicalTask = (PhysicalTask) Task;
        physicalTask.addSubtask(new Subtask("subtask1", "subtask1", 1));
      }
    }

    @Test
    void getAddress() {
        assertEquals("address", physicalTask.getAddress());
    }

    @Test
    void setAddress() {
        physicalTask.setAddress("newAddress");
        assertEquals("newAddress", physicalTask.getAddress());
    }

    @Test
    void getWorkDayDuration() {
        assertEquals(8, physicalTask.getWorkDayDuration());
    }

    @Test
    void getWorkWeekDuration() {
        assertEquals(6, physicalTask.getWorkWeekDuration());
    }

    @Test
    void saveToMemento() {
        TaskMemento taskMemento = physicalTask.saveToMemento();
        assertEquals(physicalTask.getTaskId(), taskMemento.getTaskId());
        assertEquals(physicalTask.getDescription(), taskMemento.getDescription());
        assertEquals(physicalTask.getResponsiblePerson(), taskMemento.getResponsiblePerson());
        assertEquals(physicalTask.getProgress(), taskMemento.getProgress());
        assertEquals(physicalTask.getState(), taskMemento.getState());
        assertEquals(physicalTask.getTaskPriorityStrategy(), taskMemento.getTaskPriorityStrategy());
        assertEquals(physicalTask.getDeadline(), taskMemento.getDeadline());
        assertEquals(physicalTask.getComplexity(), taskMemento.getComplexity());
        assertEquals(physicalTask.isApproved(), taskMemento.isApproved());
        assertEquals(physicalTask.getSubtasks().size(), taskMemento.getSubtasks().size());
        assertEquals(physicalTask.getAddress(), ((PhysicalTaskMemento) taskMemento).getAddress());
        assertEquals(physicalTask.getSubtasks().get(0).getName(), taskMemento.getSubtasks().get(0).getName());
        assertEquals(physicalTask.getSubtasks().get(0).getDescription(), taskMemento.getSubtasks().get(0).getDescription());
        assertEquals(physicalTask.getSubtasks().get(0).getHoursNeeded(), taskMemento.getSubtasks().get(0).getHoursNeeded());
    }

    @Test
    void restoreFromMemento() {
        TaskMemento taskMemento = physicalTask.saveToMemento();
        PhysicalTask newPhysicalTask = new PhysicalTask("2", "newDescription", "newResponsiblePerson", "newAddress", LocalDate.now(), 2, taskPriorityStrategy);
        newPhysicalTask.restoreFromMemento(taskMemento);
        assertEquals(physicalTask.getTaskId(), newPhysicalTask.getTaskId());
        assertEquals(physicalTask.getDescription(), newPhysicalTask.getDescription());
        assertEquals(physicalTask.getResponsiblePerson(), newPhysicalTask.getResponsiblePerson());
        assertEquals(physicalTask.getProgress(), newPhysicalTask.getProgress());
        assertEquals(physicalTask.getState(), newPhysicalTask.getState());
        assertEquals(physicalTask.getTaskPriorityStrategy(), newPhysicalTask.getTaskPriorityStrategy());
        assertEquals(physicalTask.getDeadline(), newPhysicalTask.getDeadline());
        assertEquals(physicalTask.getComplexity(), newPhysicalTask.getComplexity());
        assertEquals(physicalTask.isApproved(), newPhysicalTask.isApproved());
        assertEquals(physicalTask.getSubtasks().size(), newPhysicalTask.getSubtasks().size());
        assertEquals(physicalTask.getAddress(), ((PhysicalTask) newPhysicalTask).getAddress());
        assertEquals(physicalTask.getSubtasks().get(0).getName(), newPhysicalTask.getSubtasks().get(0).getName());
        assertEquals(physicalTask.getSubtasks().get(0).getDescription(), newPhysicalTask.getSubtasks().get(0).getDescription());
        assertEquals(physicalTask.getSubtasks().get(0).getHoursNeeded(), newPhysicalTask.getSubtasks().get(0).getHoursNeeded());
    }

}
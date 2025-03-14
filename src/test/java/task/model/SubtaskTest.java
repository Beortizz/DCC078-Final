package task.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.memento.SubtaskMemento;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
class SubtaskTest {
    Subtask subtask;

    @BeforeEach
    void setUp() {
        subtask = new Subtask("subtask1", "subtask1", 1);
    }
    @Test
    void getName() {
        assertEquals("subtask1", subtask.getName());
    }

    @Test
    void getDescription() {
        assertEquals("subtask1", subtask.getDescription());
    }

    @Test
    void setName() {
        subtask.setName("newName");
        assertEquals("newName", subtask.getName());
    }

    @Test
    void setDescription() {
        subtask.setDescription("newDescription");
        assertEquals("newDescription", subtask.getDescription());
    }

    @Test
    void getHoursNeeded() {
        assertEquals(1, subtask.getHoursNeeded());
    }

    @Test
    void setHoursNeeded() {
        subtask.setHoursNeeded(2);
        assertEquals(2, subtask.getHoursNeeded());
    }
    @Test
    void setHoursNeededBelowZero(){
        assertThrows(IllegalArgumentException.class, () -> {
            subtask.setHoursNeeded(-2);
        });
    }

    @Test
    void isDone() {
        assertFalse(subtask.isDone());
    }

    @Test
    void setDone() {
        subtask.setDone(true);
        assertTrue(subtask.isDone());
    }

    @Test
    void saveToMemento() {
        SubtaskMemento subtaskMemento = subtask.saveToMemento();
        assertEquals(subtask.getName(), subtaskMemento.getName());
        assertEquals(subtask.getDescription(), subtaskMemento.getDescription());
        assertEquals(subtask.getHoursNeeded(), subtaskMemento.getHoursNeeded());
        assertEquals(subtask.isDone(), subtaskMemento.isDone());
    }

    @Test
    void restoreFromMemento() {
        SubtaskMemento subtaskMemento = subtask.saveToMemento();
        Subtask newSubtask = new Subtask("newName", "newDescription", 2);
        newSubtask.restoreFromMemento(subtaskMemento);
        assertEquals(subtask.getName(), newSubtask.getName());
        assertEquals(subtask.getDescription(), newSubtask.getDescription());
        assertEquals(subtask.getHoursNeeded(), newSubtask.getHoursNeeded());
        assertEquals(subtask.isDone(), newSubtask.isDone());
    }
}
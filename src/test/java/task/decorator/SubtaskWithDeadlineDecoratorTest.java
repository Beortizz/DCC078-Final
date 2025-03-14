package task.decorator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.model.Subtask;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SubtaskWithDeadlineDecoratorTest {

    private SubtaskWithDeadlineDecorator subtaskWithDeadline;
    private Subtask subtask;

    @BeforeEach
    void setUp() {
        subtask = new Subtask("1", "Test subtask", 3);
        subtaskWithDeadline = new SubtaskWithDeadlineDecorator(subtask, LocalDate.now().plusDays(5));
    }

    @Test
    void getDeadline() {
        assertEquals(LocalDate.now().plusDays(5), subtaskWithDeadline.getDeadline());
    }

    @Test
    void setDeadline() {
        subtaskWithDeadline.setDeadline(LocalDate.now().plusDays(10));
        assertEquals(LocalDate.now().plusDays(10), subtaskWithDeadline.getDeadline());
    }

    @Test
    void isDeadlineApproachingFalse() {
        assertFalse(subtaskWithDeadline.isDeadlineApproaching());
    }
    @Test
    void isDeadlineApproachingTrue() {
        subtaskWithDeadline.setDeadline(LocalDate.now().plusDays(1));
        assertTrue(subtaskWithDeadline.isDeadlineApproaching());
    }



}
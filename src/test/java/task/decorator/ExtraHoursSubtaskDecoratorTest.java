package task.decorator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.model.ISubtask;
import task.model.Subtask;

import static org.junit.jupiter.api.Assertions.*;

class ExtraHoursSubtaskDecoratorTest {

    private ExtraHoursSubtaskDecorator extraHoursSubtask;

    @BeforeEach
    void setUp() {
        ISubtask subtask = new Subtask("1", "Test subtask", 3); // Certifique-se de que Subtask implementa ISubtask
        extraHoursSubtask = new ExtraHoursSubtaskDecorator(subtask, 2);
    }


    @Test
    void getExtraHours() {
        assertEquals(2, extraHoursSubtask.getExtraHours());
    }

    @Test
    void setExtraHours() {
        extraHoursSubtask.setExtraHours(3);
        assertEquals(3, extraHoursSubtask.getExtraHours());
    }

    @Test
    void getHoursNeeded() {
        assertEquals(5, extraHoursSubtask.getHoursNeeded());
    }
    @Test
    void getHoursNeededNegative() {
        assertThrows(IllegalArgumentException.class, () -> extraHoursSubtask.setExtraHours(-1));
    }

}
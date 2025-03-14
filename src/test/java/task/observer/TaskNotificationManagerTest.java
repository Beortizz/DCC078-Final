package task.observer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.model.PhysicalTask;
import task.model.Task;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TaskNotificationManagerTest {
    private Task task;

    @BeforeEach
    public void setup() {
        TaskNotificationManager taskNotificationManager = new TaskNotificationManager();
        task = new PhysicalTask("1", "description", "responsiblePerson", "address",  LocalDate.of(2025,03,31), 1, null);
        task.addObserver(taskNotificationManager);
    }

    @Test
    public void testTaskStateChangeNotification() {
        task.changeToInProgress();

        String expectedMessage = String.format(
                "Notificação: A tarefa '%s' (ID: %s) mudou para o estado '%s'. Responsável: %s.",
                task.getDescription(), task.getTaskId(), task.getStateName(), task.getResponsiblePerson()
        );

        assertEquals("Notificação: A tarefa 'description' (ID: 1) mudou para o estado 'Em andamento'. Responsável: responsiblePerson.", expectedMessage);
    }

}
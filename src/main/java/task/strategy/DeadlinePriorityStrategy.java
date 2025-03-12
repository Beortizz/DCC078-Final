package task.strategy;

import task.model.Task;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DeadlinePriorityStrategy implements ITaskPriorityStrategy {
    @Override
    public int calculatePriority(Task task) {
        long daysRemaining = ChronoUnit.DAYS.between(LocalDate.now(), task.getDeadline());

        if (daysRemaining <= 0) {
            return 100;
        } else if (daysRemaining >= 100) {
            return 1;
        } else {
            return 100 - (int) daysRemaining; // Quanto menos dias restantes maior a prioridade, que é um valor de 1 a 100
        }
    }

}

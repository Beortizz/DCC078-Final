package task.observer;

import task.model.Task;

import java.util.Observable;
import java.util.Observer;

public class TaskNotificationManager implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Task) {
            Task task = (Task) o;
            String mensagem = String.format(
                    "Notificação: A tarefa '%s' (ID: %s) mudou para o estado '%s'. Responsável: %s.",
                    task.getDescription(), task.getTaskId(), task.getStateName(), task.getResponsiblePerson()
            );

            gerarLog(mensagem);
        }
    }
    private String gerarLog(String mensagem) {
        return "Log gerado: " + mensagem;
    }
}
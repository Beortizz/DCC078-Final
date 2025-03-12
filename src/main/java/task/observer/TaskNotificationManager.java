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
            System.out.println(mensagem);
            gerarLog(mensagem);
        }
    }
    private void gerarLog(String mensagem) {
        // Simulação de geração de log
        System.out.println("Log gerado: " + mensagem);
    }
}
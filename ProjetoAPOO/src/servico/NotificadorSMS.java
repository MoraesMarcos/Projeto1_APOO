package servico;

import model.Cliente;

public class NotificadorSMS implements Notificador {
    @Override
    public void notificar(Cliente cliente) {
        System.out.printf("Enviando SMS para %s (%s)\n", cliente.getNome(), cliente.getTelefone());
    }
}
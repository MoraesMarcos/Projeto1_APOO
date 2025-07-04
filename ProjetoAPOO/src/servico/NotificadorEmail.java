package servico;

import model.Cliente;

public class NotificadorEmail implements Notificador {
    @Override
    public void notificar(Cliente cliente) {
        System.out.printf("Enviando e-mail para %s (%s)\n", cliente.getNome(), cliente.getEmail());
    }
}
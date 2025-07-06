package servico;

import model.Cliente;

public class NotificadorComLog implements Notificador {
    private final Notificador notificador;

    public NotificadorComLog(Notificador notificador) {
        this.notificador = notificador;
    }

    @Override
    public void notificar(Cliente cliente) {
        System.out.println("[LOG] Notificando cliente: " + cliente.getNome());
        notificador.notificar(cliente);
    }
}

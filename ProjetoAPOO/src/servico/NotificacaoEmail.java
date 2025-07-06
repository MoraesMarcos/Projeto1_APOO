package servico;

import model.Cliente;

public class NotificacaoEmail implements Notificador {
    @Override
    public void notificar(Cliente cliente) {
        System.out.println("Enviando e-mail para " + cliente.getEmail());
    }
}
package servico;

import model.Cliente;

public class NotificacaoSMS implements Notificador {
    @Override
    public void notificar(Cliente cliente) {
        System.out.println("Enviando SMS para " + cliente.getTelefone());
    }
}
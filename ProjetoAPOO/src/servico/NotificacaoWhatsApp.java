package servico;

import model.Cliente;

public class NotificacaoWhatsApp implements Notificador {
    @Override
    public void notificar(Cliente cliente) {
        System.out.println("Enviando WhatsApp para " + cliente.getTelefone());
    }
}
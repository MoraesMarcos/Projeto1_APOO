package servico;

import model.Cliente;

public class NotificadorWhatsApp implements Notificador {
    @Override
    public void notificar(Cliente cliente) {
        System.out.printf("Enviando mensagem WhatsApp para %s (%s)\n", cliente.getNome(), cliente.getTelefone());
        // Lógica real de envio de WhatsApp aqui
    }
}
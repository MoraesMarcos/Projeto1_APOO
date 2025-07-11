package servico;

import model.Cliente;

public class NotificarWhatsApp {
    public void notificar(Cliente cliente) {
        System.out.println("Notificação enviada por WhatsApp para: " + cliente.getNome() + " (" + cliente.getTelefone() + ")");
    }
}
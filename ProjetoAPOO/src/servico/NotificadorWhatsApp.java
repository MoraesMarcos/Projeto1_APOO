package servico;

import model.Cliente;

public class NotificadorWhatsApp {
    public void notificar(Cliente cliente) {
        System.out.println("Enviando WhatsApp para " + cliente.getCpf() + ": Seu pedido foi confirmado!");
    }
}

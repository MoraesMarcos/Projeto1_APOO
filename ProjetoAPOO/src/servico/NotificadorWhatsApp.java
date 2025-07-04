package servico;

import model.Cliente;

public class NotificadorWhatsApp implements INotificador {
    @Override
    public void notificar(Cliente cliente) {
        System.out.println("Enviando WhatsApp para " + cliente.getTelefone() + ": Seu pedido foi confirmado!");
    }
}
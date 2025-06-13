package servico;

import model.Cliente;

public class NotificadorSMS {
    public void notificar(Cliente cliente) {
        System.out.println("Enviando SMS para " + cliente.getTelefone() + ": Seu pedido foi confirmado!");
    }
}

package servico;

import model.Cliente;

public class NotificadorEmail {
    public void notificar(Cliente cliente) {
        System.out.println("Enviando e-mail para " + cliente.getEmail() + ": Seu pedido foi confirmado!");
    }
}

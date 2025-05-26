package servico;

import model.Pedido;

public class NotificadorEmail {

    public void enviarNotificacao(Pedido pedido) {
        String emailCliente = pedido.getCliente().getEmail();
        System.out.printf("Enviando e-mail para %s: Seu pedido foi confirmado!", emailCliente);
    }
}
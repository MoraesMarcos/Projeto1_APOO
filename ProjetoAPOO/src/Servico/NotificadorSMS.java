package servico;

import model.Pedido;

public class NotificadorSMS {

    public void enviarNotificacao(Pedido pedido) {
        String cpfCliente = pedido.getCliente().getCpf(); // Usando CPF como proxy para o número
        System.out.println("Enviando SMS para o número associado ao CPF " + cpfCliente + ": Seu pedido foi confirmado!");
    }
}
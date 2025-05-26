package servico;

public class NotificadorWhatsApp {

    public void enviarNotificacao(Pedido pedido) {
        String cpfCliente = pedido.getCliente().getCpf(); // Usando CPF como proxy para o número
        System.out.println("Enviando WhatsApp para o número associado ao CPF " + cpfCliente + ": Seu pedido foi confirmado!");
    }
}
package servico;

import model.Cliente;

public class NotificarEmail {
    public void notificar(Cliente cliente) {
        System.out.println("Notificação enviada por Email para: " + cliente.getNome() + " (" + cliente.getEmail() + ")");
    }
}
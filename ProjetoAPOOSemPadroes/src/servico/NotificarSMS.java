package servico;

import model.Cliente;

public class NotificarSMS {
    public void notificar(Cliente cliente) {
        System.out.println("Notificação enviada por SMS para: " + cliente.getNome() + " (" + cliente.getTelefone() + ")");
    }
}
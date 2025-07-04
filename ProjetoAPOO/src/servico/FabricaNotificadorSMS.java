package servico;

public class FabricaNotificadorSMS implements FabricaNotificador {
    @Override
    public Notificador criarNotificador() {
        return new NotificadorSMS();
    }
}
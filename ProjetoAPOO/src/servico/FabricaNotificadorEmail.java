package servico;

public class FabricaNotificadorEmail implements FabricaNotificador {
    @Override
    public Notificador criarNotificador() {
        return new NotificadorEmail();
    }
}
package servico;

public class FabricaNotificadorWhatsApp implements FabricaNotificador {
    @Override
    public Notificador criarNotificador() {
        return new NotificadorWhatsApp();
    }
}
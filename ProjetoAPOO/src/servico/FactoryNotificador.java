package servico;

public class FactoryNotificador {
    public static Notificador criarNotificador(int tipo) {
        return switch (tipo) {
            case 1 -> new NotificacaoEmail();
            case 2 -> new NotificacaoSMS();
            case 3 -> new NotificacaoWhatsApp();
            default -> throw new IllegalArgumentException("Tipo de notificação inválido");
        };
    }
}

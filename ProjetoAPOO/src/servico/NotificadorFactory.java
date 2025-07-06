package servico;

import java.util.Objects;

public class NotificadorFactory {
    public static Notificador criarNotificador(String tipo) {
        if (Objects.equals(tipo, "EMAIL")) {
            return new NotificacaoEmail();
        } else if (Objects.equals(tipo, "SMS")) {
            return new NotificacaoSMS();
        } else if (Objects.equals(tipo, "WHATSAPP")) {
            return new NotificacaoWhatsApp();
        } else {
            throw new IllegalArgumentException("Tipo de notificador inválido: " + tipo);
        }
    }
}